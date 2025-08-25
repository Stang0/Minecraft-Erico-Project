package com.example.examplemod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.slf4j.Logger;

// Configuration class for AAA Particles weapon effects
// Manages all settings related to particle effects when using weapons
@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    
    // AAA Particles Configuration
    private static final ForgeConfigSpec.BooleanValue ENABLE_WEAPON_EFFECTS = BUILDER
            .comment("Enable particle effects when attacking with weapons")
            .define("enableWeaponEffects", true);
    
    private static final ForgeConfigSpec.DoubleValue EFFECT_INTENSITY = BUILDER
            .comment("Intensity of particle effects (0.5 - 3.0)")
            .defineInRange("effectIntensity", 1.0, 0.5, 3.0);
    
    private static final ForgeConfigSpec.IntValue PARTICLE_DENSITY = BUILDER
            .comment("Density of particles (10 - 100)")
            .defineInRange("particleDensity", 50, 10, 100);
    
    private static final ForgeConfigSpec.BooleanValue USE_AAA_PARTICLES = BUILDER
            .comment("Use AAA Particles for enhanced effects (requires AAA Particles mod)")
            .define("useAAAParticles", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    // AAA Particles Variables
    public static boolean enableWeaponEffects = true;
    public static double effectIntensity = 1.0;
    public static int particleDensity = 50;
    public static boolean useAAAParticles = true;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        LOGGER.info("Loading AAA Particles config...");
        
        // Load AAA Particles Config
        enableWeaponEffects = ENABLE_WEAPON_EFFECTS.get();
        effectIntensity = EFFECT_INTENSITY.get();
        particleDensity = PARTICLE_DENSITY.get();
        useAAAParticles = USE_AAA_PARTICLES.get();
                
        // Display config information after loading
        LOGGER.info("AAA Particles config loaded successfully!");
        LOGGER.info("enableWeaponEffects: {}", enableWeaponEffects);
        LOGGER.info("effectIntensity: {}", effectIntensity);
        LOGGER.info("particleDensity: {}", particleDensity);
        LOGGER.info("useAAAParticles: {}", useAAAParticles);
    }
}
