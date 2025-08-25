package com.example.examplemod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;

/**
 * Effekseer Particle Effects using AAA Particles mod
 * This class handles .efkefc effects for weapon attacks
 */
public class EffekseerParticleEffects {
    
    // Static ParticleEmitterInfo instances for different weapon effects
    // You need to place your .efkefc files in assets/examplemod/effeks/
    private static final ParticleEmitterInfo SWORD_EFFECT = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "sword_effect"));
    private static final ParticleEmitterInfo SWORD_SPIRAL = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "sword_spiral"));
    private static final ParticleEmitterInfo SWORD_FIREBALL = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "sword_fireball"));
    private static final ParticleEmitterInfo SWORD_BURST = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "sword_burst"));
    
    private static final ParticleEmitterInfo AXE_WAVE = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "axe_wave"));
    private static final ParticleEmitterInfo AXE_DEBRIS = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "axe_debris"));
    private static final ParticleEmitterInfo AXE_IMPACT = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "axe_impact"));
    
    private static final ParticleEmitterInfo TRIDENT_LIGHTNING = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "trident_lightning"));
    private static final ParticleEmitterInfo TRIDENT_WATER = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "trident_water"));
    private static final ParticleEmitterInfo TRIDENT_SPLASH = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "trident_splash"));
    private static final ParticleEmitterInfo TRIDENT_CIRCLE = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "trident_circle"));
    
    private static final ParticleEmitterInfo GENERAL_CRIT = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "general_crit"));
    private static final ParticleEmitterInfo GENERAL_ENCHANTED = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "general_enchanted"));
    
    /**
     * Play an Effekseer effect at the specified location
     */
    private static void playEffect(ServerLevel level, Vec3 position, ParticleEmitterInfo emitterInfo, float scale) {
        if (!Config.enableWeaponEffects) {
            return;
        }
        
        try {
            // Apply config intensity as scale modifier
            float adjustedScale = scale * (float) Config.effectIntensity;
            
            // Clone the emitter info and set position and scale
            ParticleEmitterInfo clonedEmitter = emitterInfo.clone()
                    .position(position.x, position.y, position.z)
                    .scale(adjustedScale);
            
            // Play the effect using AAA Particles API
            AAALevel.addParticle(level, false, clonedEmitter);
            
        } catch (Exception e) {
            // Log error but don't crash the game
            ExampleMod.LOGGER.error("Failed to play Effekseer effect: " + emitterInfo.toString(), e);
        }
    }
    
    /**
     * Create sword effects using Effekseer particles
     */
    public static void createSwordEffects(ServerLevel level, Vec3 position) {
        if (!Config.enableWeaponEffects) return;
        
        // Main sword effect - circular fire around target
        playEffect(level, position, SWORD_EFFECT, 1.0f);
        
        // Spiral effect
        playEffect(level, position.add(0, 0.5, 0), SWORD_SPIRAL, 0.8f);
        
        // Fireball effect
        playEffect(level, position, SWORD_FIREBALL, 1.2f);
        
        // Burst effect for extra impact
        playEffect(level, position, SWORD_BURST, 0.9f);
    }
    
    /**
     * Create axe effects using Effekseer particles
     */
    public static void createAxeEffects(ServerLevel level, Vec3 position) {
        if (!Config.enableWeaponEffects) return;
        
        // Wave effect - ground impact
        playEffect(level, position, AXE_WAVE, 1.5f);
        
        // Debris effect - wood and stone particles
        playEffect(level, position.add(0, 0.5, 0), AXE_DEBRIS, 1.0f);
        
        // Heavy impact effect
        playEffect(level, position, AXE_IMPACT, 1.3f);
    }
    
    /**
     * Create trident effects using Effekseer particles
     */
    public static void createTridentEffects(ServerLevel level, Vec3 position) {
        if (!Config.enableWeaponEffects) return;
        
        // Lightning strike from above
        Vec3 skyPosition = position.add(0, 5, 0);
        playEffect(level, skyPosition, TRIDENT_LIGHTNING, 1.0f);
        
        // Water spiral effect
        playEffect(level, position, TRIDENT_WATER, 1.2f);
        
        // Splash effect
        playEffect(level, position, TRIDENT_SPLASH, 1.1f);
        
        // Circle effect around impact
        playEffect(level, position, TRIDENT_CIRCLE, 1.4f);
    }
    
    /**
     * Create general attack effects using Effekseer particles
     */
    public static void createGeneralAttackEffects(ServerLevel level, Vec3 position) {
        if (!Config.enableWeaponEffects) return;
        
        // Critical hit sparkles
        playEffect(level, position.add(0, 0.5, 0), GENERAL_CRIT, 0.7f);
        
        // Enchanted hit effect
        playEffect(level, position, GENERAL_ENCHANTED, 0.8f);
    }
    
    /**
     * Create a custom effect at specified position
     * This allows you to use your own .efkefc files
     */
    public static void createCustomEffect(ServerLevel level, Vec3 position, String effectName, float scale) {
        if (!Config.enableWeaponEffects) return;
        
        ParticleEmitterInfo customEffect = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, effectName));
        playEffect(level, position, customEffect, scale);
    }
    
    /**
     * Test method to play a specific effect - useful for debugging
     */
    public static void testEffect(ServerLevel level, Vec3 position, String effectName) {
        ParticleEmitterInfo testEffect = new ParticleEmitterInfo(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, effectName));
        playEffect(level, position, testEffect, 1.0f);
    }
}