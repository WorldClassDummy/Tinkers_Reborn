package tinkers.dummy.item.attribute;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record TinkersBindingComponent(ToolPart part, PartMaterial material) {
    public static final Codec<TinkersBindingComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ToolPart.CODEC.fieldOf("part").forGetter(TinkersBindingComponent::part),
                    PartMaterial.CODEC.fieldOf("material").forGetter(TinkersBindingComponent::material)
            ).apply(instance, TinkersBindingComponent::new));

    public static final StreamCodec<ByteBuf, TinkersBindingComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(ToolPart.CODEC), TinkersBindingComponent::part,
            ByteBufCodecs.fromCodec(PartMaterial.CODEC), TinkersBindingComponent::material,
            TinkersBindingComponent::new
    );
}
