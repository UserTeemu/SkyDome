package dev.userteemu.skydome.asm.tweaker;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import dev.userteemu.skydome.asm.modifications.EntityRendererTransformer;
import dev.userteemu.skydome.SkyDomeMain;
import dev.userteemu.skydome.asm.tweaker.transformer.ITransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.io.IOException;
import java.util.Collection;

public class ClassTransformer implements IClassTransformer {
    private final Multimap<String, ITransformer> transformerMap = ArrayListMultimap.create();
    private boolean hasOptifine = false;

    public ClassTransformer() {
        try { // taken from Patcher: https://discordapp.com/channels/411619823445999637/576841425103093761/757302072096849981
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader("Config");
            classReader.accept(classNode, ClassReader.SKIP_CODE);
            for (FieldNode fieldNode : classNode.fields) {
                if (fieldNode.name.equals("OF_RELEASE")) {
                    hasOptifine = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            SkyDomeMain.LOGGER.info("Something went wrong, or the user doesn't have Optifine");
        }

        registerTransformer(new EntityRendererTransformer());
    }

    private void registerTransformer(ITransformer transformer) {
        for (String cls : transformer.getClassName()) {
            transformerMap.put(cls, transformer);
        }
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) return null;

        Collection<ITransformer> transformers = transformerMap.get(transformedName);
        if (transformers.isEmpty()) return bytes;

        SkyDomeMain.LOGGER.info("Found {} transformers for {}", transformers.size(), transformedName);

        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, ClassReader.EXPAND_FRAMES);

        transformers.forEach(transformer -> {
            SkyDomeMain.LOGGER.info("Applying transformer {} on {}...", transformer.getClass().getName(), transformedName);
            transformer.transform(node, transformedName, hasOptifine);
        });

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        try {
            node.accept(writer);
        } catch (Throwable t) {
            SkyDomeMain.LOGGER.error("Exception when transforming " + transformedName + ": " + t.getClass().getSimpleName());
            t.printStackTrace();
        }

        return writer.toByteArray();
    }
}