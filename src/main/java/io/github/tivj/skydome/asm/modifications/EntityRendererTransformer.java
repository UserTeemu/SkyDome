package io.github.tivj.skydome.asm.modifications;

import io.github.tivj.skydome.asm.tweaker.transformer.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

public class EntityRendererTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"net.minecraft.client.renderer.EntityRenderer"};
    }

    @Override
    public void transform(ClassNode classNode, String name, boolean hasOptifine) {
        for (MethodNode methodNode : classNode.methods) {
            String methodName = mapMethodName(classNode, methodNode);
            if (methodName.equals("func_175068_a") || methodName.equals("renderWorldPass")) {
                ListIterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                while (iterator.hasNext()) {
                    AbstractInsnNode node = iterator.next();
                    if (hasOptifine) {
                        if (
                                node.getOpcode() == Opcodes.INVOKESTATIC && ((MethodInsnNode)node).owner.equals("Config") && ((MethodInsnNode)node).name.equals("isSkyEnabled") &&
                                node.getPrevious().getPrevious().getPrevious().getPrevious().getOpcode() == Opcodes.INVOKEINTERFACE &&
                                node.getNext().getNext().getNext().getNext().getNext().getOpcode() == Opcodes.IFEQ
                        ) {
                            String interfaceName = mapMethodNameFromNode(node.getPrevious().getPrevious().getPrevious().getPrevious());
                            if (interfaceName.equals("func_78547_a") || interfaceName.equals("setPosition")) {
                                methodNode.instructions.insertBefore(node, renderSkydome(((JumpInsnNode) node.getNext().getNext().getNext().getNext().getNext()).label));
                            }
                        }
                    } else {
                        if (node.getOpcode() == Opcodes.GETFIELD && node.getNext().getNext().getOpcode() == Opcodes.IF_ICMPLT && node.getNext().getOpcode() == Opcodes.ICONST_4) {
                            String fieldName = mapFieldNameFromNode(node);
                            if (fieldName.equals("field_72739_F") || fieldName.equals("renderDistanceChunks")) {
                                methodNode.instructions.insertBefore(node.getPrevious().getPrevious().getPrevious(), renderSkydome(((JumpInsnNode) node.getNext().getNext()).label));
                            }
                        }
                    }
                }
            }
        }
    }

    private InsnList renderSkydome(LabelNode label) {
        InsnList list = new InsnList();
        LabelNode end = new LabelNode();
        list.add(new FieldInsnNode(Opcodes.GETSTATIC, "io/github/tivj/skydome/SkyDomeMain", "INSTANCE", "Lio/github/tivj/skydome/SkyDomeMain;"));
        list.add(new FieldInsnNode(Opcodes.GETFIELD, "io/github/tivj/skydome/SkyDomeMain", "config", "Lio/github/tivj/skydome/SkyDomeConfig;"));
        list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "io/github/tivj/skydome/SkyDomeConfig", "isEnabled", "()Z", false));
        list.add(new JumpInsnNode(Opcodes.IFEQ, end));
        list.add(new FieldInsnNode(Opcodes.GETSTATIC, "io/github/tivj/skydome/SkyDomeMain", "INSTANCE", "Lio/github/tivj/skydome/SkyDomeMain;"));
        list.add(new FieldInsnNode(Opcodes.GETFIELD, "io/github/tivj/skydome/SkyDomeMain", "renderer", "Lio/github/tivj/skydome/SkydomeRenderer;"));
        list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "io/github/tivj/skydome/SkydomeRenderer", "renderSky",  "()V", false));
        list.add(new JumpInsnNode(Opcodes.GOTO, label));
        list.add(end);
        return list;
    }
}
