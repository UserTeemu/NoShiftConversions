package dev.userteemu.noshiftconversions.asm.transformers;

import com.google.common.collect.Sets;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import net.minecraftforge.fml.loading.FMLLoader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import javax.annotation.Nonnull;
import java.util.Set;

public class IForgeBlockTransformer implements ITransformer<MethodNode> {

    @Nonnull
    @Override
    public MethodNode transform(MethodNode input, ITransformerVotingContext context) {
        input.instructions.insert(addShiftingCheck());
        return input;
    }

    @Nonnull
    @Override
    public TransformerVoteResult castVote(ITransformerVotingContext context) {
        return TransformerVoteResult.YES;
    }

    @Nonnull
    @Override
    public Set<Target> targets() {
        return Sets.newHashSet(
                Target.targetMethod("net.minecraftforge.common.extensions.IForgeBlock", "getToolModifiedState", "(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraftforge/common/ToolType;)Lnet/minecraft/block/BlockState;")
        );
    }

    private static InsnList addShiftingCheck() {
        InsnList list = new InsnList();
        LabelNode end = new LabelNode();

        list.add(new VarInsnNode(Opcodes.ALOAD, 4));
        list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/entity/player/PlayerEntity", isSneaking(), "()Z", false));
        list.add(new JumpInsnNode(Opcodes.IFEQ, end));

        list.add(new InsnNode(Opcodes.ACONST_NULL));
        list.add(new InsnNode(Opcodes.ARETURN));

        list.add(end);

        return list;
    }

    private static String isSneaking() {
        String mcp = "isSneaking";
        String srg = "func_225608_bj_";

        if (FMLLoader.getNaming().equals("srg")) return srg;
        else return mcp;
    }
}
