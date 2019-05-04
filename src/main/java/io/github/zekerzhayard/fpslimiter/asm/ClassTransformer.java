package io.github.zekerzhayard.fpslimiter.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class ClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String className, String transformedName, byte[] basicClass) {
        if (transformedName.equals("net.minecraft.client.Minecraft")) {
            System.out.println("Found the class: " + className);
            ClassNode cn = new ClassNode();
            new ClassReader(basicClass).accept(cn, ClassReader.EXPAND_FRAMES);
            for (MethodNode mn : cn.methods) {
                if (FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(className, mn.name, mn.desc).equals("func_90020_K") && mn.desc.equals("()I")) {
                    System.out.println("Found the method: " + mn.name + mn.desc);
                    for (AbstractInsnNode ain : mn.instructions.toArray()) {
                        if (ain.getOpcode() == Opcodes.IRETURN) {
                            System.out.println("Found the node: " + ain.getOpcode() + " " + ain.getType());
                            mn.instructions.insertBefore(ain, new MethodInsnNode(Opcodes.INVOKESTATIC, "FpsLimiterHook", "getInactiveLimitFramerate", "(I)I", false));
                        }
                    }
                }
            }
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            cn.accept(cw);
            return cw.toByteArray();
        }
        return basicClass;
    }
}
