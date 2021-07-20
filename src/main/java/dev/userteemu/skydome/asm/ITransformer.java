package dev.userteemu.skydome.asm;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.tree.*;

public interface ITransformer {
    String[] getClassName();
    void transform(ClassNode classNode, String name, boolean hasOptifine);

    default String mapMethodName(ClassNode classNode, MethodNode methodNode) {
        return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, methodNode.name, methodNode.desc);
    }

    default String mapMethodDesc(MethodNode methodNode) {
        return FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(methodNode.desc);
    }

    //Huge thanks to LlamaLad7#7444 for these 2
    default String mapMethodNameFromNode(AbstractInsnNode node) {
        MethodInsnNode methodInsnNode = (MethodInsnNode) node;
        return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(methodInsnNode.owner, methodInsnNode.name, methodInsnNode.desc);
    }
    default String mapFieldNameFromNode(AbstractInsnNode node) {
        FieldInsnNode methodInsnNode = (FieldInsnNode) node;
        return FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(methodInsnNode.owner, methodInsnNode.name, methodInsnNode.desc);
    }
}
