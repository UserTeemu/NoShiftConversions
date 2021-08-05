package dev.userteemu.noshiftconversions.asm;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import dev.userteemu.noshiftconversions.asm.transformers.IForgeBlockTransformer;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class LaunchPluginService implements ITransformationService {
    @Override
    public String name() {
        return "NoShiftConversions";
    }

    @Override
    public void initialize(IEnvironment environment) {

    }

    @Override
    public void beginScanning(IEnvironment environment) {

    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) throws IncompatibleEnvironmentException {

    }

    @Nonnull
    @Override
    public List<ITransformer> transformers() {
        return Collections.singletonList(new IForgeBlockTransformer());
    }
}
