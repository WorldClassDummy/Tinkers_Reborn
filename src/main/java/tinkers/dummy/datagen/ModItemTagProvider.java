package tinkers.dummy.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, TinkersReborn.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ItemTags.PICKAXES)
                .add(ModItems.PICKAXE.get())
                .replace(false)
        ;
        tag(ItemTags.AXES)
                .replace(false)
        ;
        tag(ItemTags.SHOVELS)
                .replace(false)
        ;
        tag(ItemTags.SWORDS)
                .replace(false)
        ;

        tag(ItemTags.HOES)
                .replace(false)
        ;
    }
}
