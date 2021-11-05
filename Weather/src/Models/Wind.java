package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {
	@JsonProperty("speed")
	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	int speed;

	@JsonProperty("deg")
	public int getDeg() {
		return this.deg;
	}

	public void setDeg(int deg) {
		this.deg = deg;
	}

	int deg;
}