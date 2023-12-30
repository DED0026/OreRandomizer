package io.github.crusopaul.OreRandomizer.compat;

import io.github.crusopaul.OreRandomizer.element.RandomizationSound;
import io.github.crusopaul.OreRandomizer.element.RandomizedMaterial;
import io.github.crusopaul.OreRandomizer.exception.BadMaterialNodeException;
import io.github.crusopaul.OreRandomizer.exception.BadSoundNodeException;
import io.github.crusopaul.OreRandomizer.exception.BadVersion;
import io.github.crusopaul.OreRandomizer.exception.NegativeRatioException;
import io.github.crusopaul.OreRandomizer.list.RandomizationSoundList;
import io.github.crusopaul.OreRandomizer.list.RandomizedMaterialList;
import io.github.crusopaul.OreRandomizer.sort.MaterialListSorter;
import io.github.crusopaul.OreRandomizer.sort.SoundListSorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public class VersionEngine {
  public VersionEngine(String BukkitVersionString, FileConfiguration config)
      throws BadVersion, BadMaterialNodeException, NegativeRatioException, BadSoundNodeException,
          NullPointerException {
    final Version version = this.parseVersionString(BukkitVersionString);

    if (version == Version.None) {
      throw new BadVersion("This version is not yet supported");
    }

    this.buildLists(version);

    try {
      this.prepLists(config);
    } catch (final BadMaterialNodeException e) {
      throw e;
    } catch (final NegativeRatioException e) {
      throw e;
    } catch (final BadSoundNodeException e) {
      throw e;
    } catch (final NullPointerException e) {
      throw e;
    }
  }

  public RandomizedMaterialList randomizedMaterialList;
  public RandomizationSoundList randomizationSoundList;

  private enum Version {
    Sniff,
    Frogs,
    Goats,
    AncientDebris,
    Bees,
    PreBees,
    Concrete,
    Legacy,
    None
  }

  private Version parseVersionString(String BukkitVersionString) {
    final Version version;

    if (BukkitVersionString.contains("1.20.2")
        || BukkitVersionString.contains("1.20.1")
        || BukkitVersionString.contains("1.20")) {
      version = Version.Sniff;
    } else if (BukkitVersionString.contains("1.19.4")
        || BukkitVersionString.contains("1.19.3")
        || BukkitVersionString.contains("1.19.2")
        || BukkitVersionString.contains("1.19.1")
        || BukkitVersionString.contains("1.19")) {
      version = Version.Frogs;
    } else if (BukkitVersionString.contains("1.18.1")
        || BukkitVersionString.contains("1.18")
        || BukkitVersionString.contains("1.17.1")
        || BukkitVersionString.contains("1.17")) {
      version = Version.Goats;
    } else if (BukkitVersionString.contains("1.16.5")
        || BukkitVersionString.contains("1.16.4")
        || BukkitVersionString.contains("1.16.3")
        || BukkitVersionString.contains("1.16.2")
        || BukkitVersionString.contains("1.16.1")
        || BukkitVersionString.contains("1.16")) {
      version = Version.AncientDebris;
    } else if (BukkitVersionString.contains("1.15.2")
        || BukkitVersionString.contains("1.15.1")
        || BukkitVersionString.contains("1.15")) {
      version = Version.Bees;
    } else if (BukkitVersionString.contains("1.14.4")
        || BukkitVersionString.contains("1.14.3")
        || BukkitVersionString.contains("1.14.2")
        || BukkitVersionString.contains("1.14.1")
        || BukkitVersionString.contains("1.14")
        || BukkitVersionString.contains("1.13.2")
        || BukkitVersionString.contains("1.13.1")
        || BukkitVersionString.contains("1.13")) {
      version = Version.PreBees;
    } else if (BukkitVersionString.contains("1.12.2")
        || BukkitVersionString.contains("1.12.1")
        || BukkitVersionString.contains("1.12")) {
      version = Version.Concrete;
    } else if (BukkitVersionString.contains("1.11.2")
        || BukkitVersionString.contains("1.11.1")
        || BukkitVersionString.contains("1.11")) {
      version = Version.Legacy;
    } else {
      version = Version.None;
    }

    return version;
  }

  private void buildLists(Version version) {
    final List<RandomizedMaterial> matList = new ArrayList<RandomizedMaterial>();
    final List<RandomizationSound> sndList = new ArrayList<RandomizationSound>();

    switch (version) {
      case Sniff:
        sndList.addAll(
                Arrays.asList(
                new RandomizationSound(
                        Sound.ENTITY_SNIFFER_SCENTING,
                        "Sniff")
                )
        );
      case Frogs:
        sndList.addAll(
                Arrays.asList(
                new RandomizationSound(
                        Sound.ENTITY_FROG_AMBIENT,
                        "Wibbit")
                )
        );
      case Goats:
        matList.addAll(
            Arrays.asList(
                new RandomizedMaterial(
                    Material.COPPER_ORE,
                    Material.DEEPSLATE_COPPER_ORE,
                    "RandomSpawnRatios.Copper",
                    "Copper"),
                new RandomizedMaterial(
                    Material.AMETHYST_BLOCK,
                    Material.AMETHYST_BLOCK,
                    "RandomSpawnRatios.Amethyst",
                    "Amethyst")
            )
        );
        sndList.addAll(
            Arrays.asList(
                new RandomizationSound(
                    Sound.ENTITY_GOAT_PREPARE_RAM,
                    "Maa"
                )
            )
        );
      case AncientDebris:
        matList.addAll(
            Arrays.asList(
                new RandomizedMaterial(
                    Material.ANCIENT_DEBRIS,
                    Material.ANCIENT_DEBRIS,
                    "RandomSpawnRatios.AncientDebris",
                    "AncientDebris")));
      case Bees:
        sndList.addAll(Arrays.asList(new RandomizationSound(Sound.ENTITY_BEE_LOOP, "Buzz")));
      case PreBees:
        matList.addAll(
            Arrays.asList(
                new RandomizedMaterial(
                    Material.ANDESITE,
                    Material.ANDESITE,
                    "RandomSpawnRatios.Andesite",
                    "Andesite"),
                new RandomizedMaterial(
                    Material.DIORITE,
                    Material.DIORITE,
                    "RandomSpawnRatios.Diorite",
                    "Diorite"),
                new RandomizedMaterial(
                    Material.GRANITE,
                    Material.GRANITE,
                    "RandomSpawnRatios.Granite",
                    "Granite")
            )
        );
      case Concrete:
        matList.addAll(
            Arrays.asList(
                new RandomizedMaterial(
                    Material.BLACK_CONCRETE_POWDER,
                    Material.BLACK_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderBlack",
                    "ConcretePowderBlack"),
                new RandomizedMaterial(
                    Material.BLUE_CONCRETE_POWDER,
                    Material.BLUE_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderBlue",
                    "ConcretePowderBlue"),
                new RandomizedMaterial(
                    Material.BROWN_CONCRETE_POWDER,
                    Material.BROWN_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderBrown",
                    "ConcretePowderBrown"),
                new RandomizedMaterial(
                    Material.CYAN_CONCRETE_POWDER,
                    Material.CYAN_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderCyan",
                    "ConcretePowderCyan"),
                new RandomizedMaterial(
                    Material.GRAY_CONCRETE_POWDER,
                    Material.GRAY_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderGray",
                    "ConcretePowderGray"),
                new RandomizedMaterial(
                    Material.GREEN_CONCRETE_POWDER,
                    Material.GREEN_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderGreen",
                    "ConcretePowderGreen"),
                new RandomizedMaterial(
                    Material.LIGHT_BLUE_CONCRETE_POWDER,
                    Material.LIGHT_BLUE_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderLightBlue",
                    "ConcretePowderLightBlue"),
                new RandomizedMaterial(
                    Material.LIGHT_GRAY_CONCRETE_POWDER,
                    Material.LIGHT_GRAY_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderLightGray",
                    "ConcretePowderLightGray"),
                new RandomizedMaterial(
                    Material.LIME_CONCRETE_POWDER,
                    Material.LIME_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderLime",
                    "ConcretePowderLime"),
                new RandomizedMaterial(
                    Material.MAGENTA_CONCRETE_POWDER,
                    Material.MAGENTA_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderMagenta",
                    "ConcretePowderMagenta"),
                new RandomizedMaterial(
                    Material.ORANGE_CONCRETE_POWDER,
                    Material.ORANGE_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderOrange",
                    "ConcretePowderOrange"),
                new RandomizedMaterial(
                    Material.PINK_CONCRETE_POWDER,
                    Material.PINK_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderPink",
                    "ConcretePowderPink"),
                new RandomizedMaterial(
                    Material.PURPLE_CONCRETE_POWDER,
                    Material.PURPLE_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderPurple",
                    "ConcretePowderPurple"),
                new RandomizedMaterial(
                    Material.RED_CONCRETE_POWDER,
                    Material.RED_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderRed",
                    "ConcretePowderRed"),
                new RandomizedMaterial(
                    Material.WHITE_CONCRETE_POWDER,
                    Material.WHITE_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderWhite",
                    "ConcretePowderWhite"),
                new RandomizedMaterial(
                    Material.YELLOW_CONCRETE_POWDER,
                    Material.YELLOW_CONCRETE_POWDER,
                    "RandomSpawnRatios.ConcretePowderYellow",
                    "ConcretePowderYellow")
            )
        );
      case Legacy:
        if (version == Version.Goats) {
          matList.addAll(
              Arrays.asList(
                  new RandomizedMaterial(
                      Material.COBBLESTONE,
                      Material.COBBLED_DEEPSLATE,
                      "RandomSpawnRatios.Cobblestone",
                      "Cobblestone"),
                  new RandomizedMaterial(
                      Material.COAL_ORE,
                      Material.DEEPSLATE_COAL_ORE,
                      "RandomSpawnRatios.Coal",
                      "Coal"),
                  new RandomizedMaterial(
                      Material.DIAMOND_ORE,
                      Material.DEEPSLATE_DIAMOND_ORE,
                      "RandomSpawnRatios.Diamond",
                      "Diamond"),
                  new RandomizedMaterial(
                      Material.EMERALD_ORE,
                      Material.DEEPSLATE_EMERALD_ORE,
                      "RandomSpawnRatios.Emerald",
                      "Emerald"),
                  new RandomizedMaterial(
                      Material.NETHER_QUARTZ_ORE,
                      Material.NETHER_QUARTZ_ORE,
                      "RandomSpawnRatios.Quartz",
                      "Quartz"),
                  new RandomizedMaterial(
                      Material.GLOWSTONE,
                      Material.GLOWSTONE,
                      "RandomSpawnRatios.Glowstone",
                      "Glowstone"),
                  new RandomizedMaterial(
                      Material.GOLD_ORE,
                      Material.DEEPSLATE_GOLD_ORE,
                      "RandomSpawnRatios.Gold",
                      "Gold"),
                  new RandomizedMaterial(
                      Material.IRON_ORE,
                      Material.DEEPSLATE_IRON_ORE,
                      "RandomSpawnRatios.Iron",
                      "Iron"),
                  new RandomizedMaterial(
                      Material.LAPIS_ORE,
                      Material.DEEPSLATE_LAPIS_ORE,
                      "RandomSpawnRatios.Lapis",
                      "Lapis"),
                  new RandomizedMaterial(
                      Material.OBSIDIAN,
                      Material.OBSIDIAN,
                      "RandomSpawnRatios.Obsidian",
                      "Obsidian"),
                  new RandomizedMaterial(
                      Material.REDSTONE_ORE,
                      Material.DEEPSLATE_REDSTONE_ORE,
                      "RandomSpawnRatios.Redstone",
                      "Redstone"),
                  new RandomizedMaterial(
                      Material.STONE,
                      Material.DEEPSLATE,
                      "RandomSpawnRatios.Stone",
                      "Stone"),
                  new RandomizedMaterial(
                      Material.GRASS_BLOCK,
                      Material.DIRT,
                      "RandomSpawnRatios.Dirt",
                      "Dirt")
              )
          );
        } else {
          matList.addAll(
              Arrays.asList(
                  new RandomizedMaterial(
                      Material.COBBLESTONE,
                      Material.COBBLESTONE,
                      "RandomSpawnRatios.Cobblestone",
                      "Cobblestone"),
                  new RandomizedMaterial(
                      Material.COAL_ORE, Material.COAL_ORE,
                      "RandomSpawnRatios.Coal",
                      "Coal"),
                  new RandomizedMaterial(
                      Material.DIAMOND_ORE,
                      Material.DIAMOND_ORE,
                      "RandomSpawnRatios.Diamond",
                      "Diamond"),
                  new RandomizedMaterial(
                      Material.EMERALD_ORE,
                      Material.EMERALD_ORE,
                      "RandomSpawnRatios.Emerald",
                      "Emerald"),
                  new RandomizedMaterial(
                      Material.NETHER_QUARTZ_ORE,
                      Material.NETHER_QUARTZ_ORE,
                      "RandomSpawnRatios.Quartz",
                      "Quartz"),
                  new RandomizedMaterial(
                      Material.GLOWSTONE,
                      Material.GLOWSTONE,
                      "RandomSpawnRatios.Glowstone",
                      "Glowstone"),
                  new RandomizedMaterial(
                      Material.GOLD_ORE,
                      Material.GOLD_ORE,
                      "RandomSpawnRatios.Gold",
                      "Gold"),
                  new RandomizedMaterial(
                      Material.IRON_ORE,
                      Material.IRON_ORE,
                      "RandomSpawnRatios.Iron",
                      "Iron"),
                  new RandomizedMaterial(
                      Material.LAPIS_ORE,
                      Material.LAPIS_ORE,
                      "RandomSpawnRatios.Lapis",
                      "Lapis"),
                  new RandomizedMaterial(
                      Material.OBSIDIAN,
                      Material.OBSIDIAN,
                      "RandomSpawnRatios.Obsidian",
                      "Obsidian"),
                  new RandomizedMaterial(
                      Material.REDSTONE_ORE,
                      Material.REDSTONE_ORE,
                      "RandomSpawnRatios.Redstone",
                      "Redstone"),
                  new RandomizedMaterial(
                      Material.STONE,
                      Material.STONE,
                      "RandomSpawnRatios.Stone",
                      "Stone"),
                  new RandomizedMaterial(
                      Material.GRASS_BLOCK,
                      Material.DIRT,
                      "RandomSpawnRatios.Dirt",
                      "Dirt")
              )
          );
        }
        sndList.addAll(
            Arrays.asList(
                new RandomizationSound(
                    Sound.BLOCK_LAVA_EXTINGUISH,
                    "Normal"),
                new RandomizationSound(
                    Sound.ENTITY_CREEPER_PRIMED,
                    "Ssss")
            )
        );
        break;
      default:
        break;
    }

    Collections.sort(matList, new MaterialListSorter());
    Collections.sort(sndList, new SoundListSorter());
    randomizedMaterialList =
        new RandomizedMaterialList(matList.toArray(new RandomizedMaterial[matList.size()]));
    randomizationSoundList =
        new RandomizationSoundList(sndList.toArray(new RandomizationSound[sndList.size()]));
  }

  private void prepLists(FileConfiguration config)
      throws BadMaterialNodeException, NegativeRatioException, BadSoundNodeException,
          NullPointerException {
    try {
      randomizedMaterialList.populateRatios(config);
      randomizedMaterialList.populateThresholds();
      randomizationSoundList.setSound(config);
    } catch (final BadMaterialNodeException e) {
      throw e;
    } catch (final NegativeRatioException e) {
      throw e;
    } catch (final BadSoundNodeException e) {
      throw e;
    } catch (final NullPointerException e) {
      throw e;
    }
  }
}
