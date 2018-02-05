/**
 * Copyright 2012-2013 John Cummens (aka Shadowmage, Shadowmage4513)
 * This software is distributed under the terms of the GNU General Public License.
 * Please see COPYING for precise license information.
 * <p>
 * This file is part of Ancient Warfare.
 * <p>
 * Ancient Warfare is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Ancient Warfare is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Ancient Warfare.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.shadowmage.ancientwarfare.vehicle.render.vehicle;

import net.shadowmage.ancientwarfare.vehicle.entity.VehicleBase;
import net.shadowmage.ancientwarfare.vehicle.model.ModelSubmarine;
import net.shadowmage.ancientwarfare.vehicle.render.RenderVehicleBase;
import org.lwjgl.opengl.GL11;
import shadowmage.ancient_warfare.client.render.AWTextureManager;
import shadowmage.ancient_warfare.common.config.Config;

public class RenderSubmarine extends RenderVehicleBase {

	ModelSubmarine model = new ModelSubmarine();

	/**
	 *
	 */
	public RenderSubmarine() {

	}

	@Override
	public void renderVehicle(VehicleBase veh, double x, double y, double z, float yaw, float tick) {
		float wheelAngle = veh.wheelRotation + (tick * (veh.wheelRotation - veh.wheelRotationPrev));
		model.setWheelRotations(wheelAngle, wheelAngle, wheelAngle, wheelAngle);
		model.render(veh, 0, 0, 0, 0, 0, 0.0625f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDepthMask(false);
		AWTextureManager.bindTexture(Config.texturePath + "models/submarine_screen.png");
		model.render(veh, 0, 0, 0, 0, 0, 0.0625f);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		AWTextureManager.bindTexture(veh.getTexture());
	}

	@Override
	public void renderVehicleFlag() {
		model.renderFlag();
	}

}
