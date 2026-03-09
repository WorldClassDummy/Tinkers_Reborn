package tinkers.dummy.item.attribute;

import net.minecraft.resources.ResourceLocation;

public class ToolParts extends ToolPart {
    public ToolParts(ResourceLocation id) {
        super(id);
    }

    public static final ToolPart PICKAXE_HEAD = register("pickaxe_head");
    public static final ToolPart BINDING = register("binding");
    public static final ToolPart ROD = register("rod");


}
