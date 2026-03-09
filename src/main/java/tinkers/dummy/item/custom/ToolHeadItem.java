package tinkers.dummy.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import tinkers.dummy.item.attribute.PartMaterial;
import tinkers.dummy.item.attribute.TinkersPartComponent;
import tinkers.dummy.item.attribute.ToolMaterials;
import tinkers.dummy.item.component.ModDataComponents;

import java.util.List;
import java.util.Objects;

public class ToolHeadItem extends Item {
    public ToolHeadItem(Properties properties) {
        super(properties);
    }

    public String getMaterial(ItemStack stack) {
        TinkersPartComponent part = stack.get(ModDataComponents.PART_MATERIAL_COMPONENT);
        if (part != null) {
            return part.material().getId().getPath();
        } else {
            return null;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        if (getMaterial(stack) != null) {
            components.add(Component.translatable("tooltip.tinkersreborn.material." + getMaterial(stack)));
        }

        super.appendHoverText(stack, context, components, tooltipFlag);
    }
}
