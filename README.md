# Minecraft Erico Project

## คำอธิบาย
Mod นี้เพิ่ม particle effects อาวุธโจมตีในเกม Minecraft

## NOTE 
- effect ตอนนี้ .efkfc เป็น effect เดียวกันหมด ‼️‼️
## ฟีเจอร์หลัก

###  Sword Effects

###  Axe Effects  

###  Trident Effects



1. Minecraft Forge 47.4.0 | Minecraft 1.20.1


## การ Build Mod

```bash
# Build mod
./gradlew build

# Run client สำหรับทดสอบ
./gradlew runClient

# Run server สำหรับทดสอบ multiplayer
./gradlew runServer
```

## การปรับแต่งค่า

Mod นี้มีไฟล์ config ที่สามารถปรับแต่งได้:

- `enableWeaponEffects`: เปิด/ปิด effects ทั้งหมด
- `effectIntensity`: ปรับความเข้มของ effects (0.5-3.0)
- `particleDensity`: ปรับความหนาแน่นของ particles (10-100)

ไฟล์ config จะอยู่ที่: `src/main/java/com/example/examplemod/Config.java`

## วิธีใช้งาน

1. ถือดาบ, ขวาน, หรือตรีศูล
2. โจมตี mob หรือ player อื่น


## ระบบ

- Java 17
- Minecraft 1.20.1
- Minecraft Forge 47.4.0


## ไฟล์ที่ทำงานด้วยกัน
- ผู้เล่นโจมตีด้วยอาวุธ → WeaponEffectHandler จับ event
- ตรวจสอบประเภทอาวุธ → เลือก effect ที่เข้าเงื่อนไข wepone.get()
- เรียกใช้ AAA Particles → EffekseerParticleEffects สร้าง effect
- แสดงผล .efkefc → Effekseer engine render บนหน้าจอ
- 
## เครดิต

สร้างโดย: Erico
เวอร์ชัน: 1.0.0
License: All Rights Reserved
