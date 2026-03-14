package tinkers.dummy.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import tinkers.dummy.item.attribute.TinkersBindingComponent;
import tinkers.dummy.item.attribute.TinkersHeadComponent;
import tinkers.dummy.item.attribute.TinkersRodComponent;
import tinkers.dummy.item.component.ModDataComponents;

import java.util.List;

public class TinkersPickaxeItem extends Item {
    public TinkersPickaxeItem(Properties properties) {
        super(properties);
    }

    public String getPart(ItemStack stack, int partId) {
        TinkersHeadComponent head = stack.get(ModDataComponents.HEAD_PART_COMPONENT);
        TinkersBindingComponent binding = stack.get(ModDataComponents.BINDING_PART_COMPONENT);
        TinkersRodComponent rod = stack.get(ModDataComponents.ROD_PART_COMPONENT);
        switch (partId) {
            case 0: return head != null ? head.part().getId().getPath() : null;
            case 1: return binding != null ? binding.part().getId().getPath() : null;
            case 2: return rod != null ? rod.part().getId().getPath() : null;
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        if (getPart(stack, 0) != null) {
            components.add(Component.translatable("tooltip.tinkersreborn.part." + getPart(stack, 0)).withStyle(ChatFormatting.GRAY));
            components.add(Component.translatable("tooltip.tinkersreborn.part." + getPart(stack, 1)).withStyle(ChatFormatting.GRAY));
            components.add(Component.translatable("tooltip.tinkersreborn.part." + getPart(stack, 2)).withStyle(ChatFormatting.GRAY));
        }

        super.appendHoverText(stack, context, components, tooltipFlag);
    }
}
