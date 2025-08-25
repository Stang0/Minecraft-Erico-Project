package com.example.examplemod;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WeaponEffectHandler {
    
    private static final Random random = new Random();
    
    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        // ตรวจสอบว่าอยู่ในโลก Server หรือไม่ - ต้องเป็น server side เพื่อส่ง particles
        if (!(event.getEntity().level() instanceof ServerLevel serverLevel)) {
            return;
        }
        
        // ตรวจสอบว่าผู้โจมตีเป็น Player หรือไม่
        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }
        
        // ตรวจสอบว่าใช้อาวุธหรือไม่
        ItemStack weapon = player.getMainHandItem();
        if (!isWeapon(weapon)) {
            return;
        }
        
        LivingEntity target = event.getEntity();
        
        // สร้าง particle effects - ใช้ค่า default เนื่องจาก config เป็น client-side
        createWeaponEffects(serverLevel, player, target, weapon);
    }
    
    private static boolean isWeapon(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }
        
        return itemStack.getItem() instanceof SwordItem ||
               itemStack.getItem() instanceof AxeItem ||
               itemStack.getItem() instanceof TridentItem;
    }
    
    private static void createWeaponEffects(ServerLevel level, Player attacker, LivingEntity target, ItemStack weapon) {
        Vec3 targetPos = target.position();
        
        // Effect ที่แตกต่างกันตามประเภทอาวุธ
        if (weapon.getItem() instanceof SwordItem) {
            createSwordEffects(level, targetPos);
        } else if (weapon.getItem() instanceof AxeItem) {
            createAxeEffects(level, targetPos);
        } else if (weapon.getItem() instanceof TridentItem) {
            createTridentEffects(level, targetPos);
        }
        
        // Effect ทั่วไปสำหรับการโจมตี
        createGeneralAttackEffects(level, targetPos);
    }
    
    private static void createSwordEffects(ServerLevel level, Vec3 position) {
        // Use only Effekseer effects
        EffekseerParticleEffects.createSwordEffects(level, position);
    }
    
    private static void createAxeEffects(ServerLevel level, Vec3 position) {
        // Use only Effekseer effects
        EffekseerParticleEffects.createAxeEffects(level, position);
    }
    
    private static void createTridentEffects(ServerLevel level, Vec3 position) {
        // Use only Effekseer effects
        EffekseerParticleEffects.createTridentEffects(level, position);
    }
    
    private static void createGeneralAttackEffects(ServerLevel level, Vec3 position) {
        // Use only Effekseer effects
        EffekseerParticleEffects.createGeneralAttackEffects(level, position);
    }
}