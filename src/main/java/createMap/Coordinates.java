package createMap;

import java.util.Objects;

public class Coordinates {
	public final Integer horizontal;
	public final Integer vertical;

	
	public Coordinates(int horizontal, int vertical) {
		super();
		this.horizontal = horizontal;
		this.vertical = vertical;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(horizontal, vertical);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		return horizontal == other.horizontal && vertical == other.vertical;
	}

}
