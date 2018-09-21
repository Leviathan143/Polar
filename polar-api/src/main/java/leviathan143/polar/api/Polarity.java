package leviathan143.polar.api;

import java.io.IOException;
import java.util.Locale;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.*;
import net.minecraft.util.IStringSerializable;

public enum Polarity implements IStringSerializable
{	
	NONE(0),
	RED(1, 0),
	BLUE(2, 1);
	
	public static final Polarity[] POLARISED = new Polarity[2];
	private static final DataSerializer<Polarity> DATA_SERIALIZER = new DataSerializer<Polarity>()
	{
		@Override
		public void write(PacketBuffer buf, Polarity value) {buf.writeInt(value.getIndex());}

		@Override
		public Polarity read(PacketBuffer buf) throws IOException {return Polarity.fromIndex(buf.readInt());}

		@Override
		public DataParameter<Polarity> createKey(int id) {return new DataParameter<>(id, this);}

		@Override
		public Polarity copyValue(Polarity value) {return value;}
	}; 
	private static final Polarity[] IDX_TO_VALUE = new Polarity[values().length];
	static
	{
		for(Polarity p : values())
		{
			//Standard index
			if (IDX_TO_VALUE[p.getIndex()] != null)
				throw new IllegalArgumentException(
						String.format("Index collision between constants %s and %s", IDX_TO_VALUE[p.getIndex()], p));
			IDX_TO_VALUE[p.getIndex()] = p;
			
			//Polarised index
			if (p.getPolarisedIndex() >= 0)
			{
				if (POLARISED[p.getPolarisedIndex()] != null)
					throw new IllegalArgumentException(
							String.format("Polarised index collision between constants %s and %s", POLARISED[p.getPolarisedIndex()], p));
				POLARISED[p.getPolarisedIndex()] = p;
			}
		}
		DataSerializers.registerSerializer(DATA_SERIALIZER);
	}
	
	private final int index;
	//Index for the subset of constants that are polarised(not NONE). NONE has a polarised index of -1.
	private final int polarisedIndex;
	private final String translationKey;
	
	private Polarity(int index)
	{
		this(index, -1);
	}
	
	private Polarity(int index, int polarisedIndex)
	{
		this.index = index;
		this.polarisedIndex = polarisedIndex;
		this.translationKey = PolarAPI.PROVIDER_MOD_ID + ".polarity." + getName();
	}
	
	public static Polarity fromIndex(int index)
	{
		if(index < values().length) return IDX_TO_VALUE[index];
		throw new IllegalArgumentException("No constant exists with the index " + index);
	}
	
	public static Polarity fromPolarisedIndex(int polarisedIndex)
	{
		if(polarisedIndex < POLARISED.length) return POLARISED[polarisedIndex];
		throw new IllegalArgumentException("No constant exists with the polarised index " + polarisedIndex);
	}
	
	public static DataSerializer<Polarity> getDataSerializer()
	{
		return DATA_SERIALIZER;
	}
	
	@Override
	public String getName()
	{
		return name().toLowerCase(Locale.ROOT);
	}
	
	public String getTranslationKey()
	{
		return translationKey;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public int getPolarisedIndex()
	{
		return polarisedIndex;
	}
}
