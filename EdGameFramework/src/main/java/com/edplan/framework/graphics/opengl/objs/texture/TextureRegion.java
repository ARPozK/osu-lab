package com.edplan.framework.graphics.opengl.objs.texture;

import com.edplan.framework.graphics.opengl.objs.GLTexture;
import com.edplan.framework.math.RectF;
import com.edplan.framework.math.Vec2;
import com.edplan.framework.graphics.opengl.objs.AbstractTexture;
import com.edplan.framework.math.RectI;
import com.edplan.framework.math.Quad;
import com.edplan.framework.math.IQuad;

public class TextureRegion extends AbstractTexture {
    private GLTexture texture;

    private RectF area = new RectF();

    private Quad rawQuad;

    private int width, height;

    public TextureRegion(GLTexture t, RectF _area) {
        this.texture = t;
        setArea(_area);
    }

    public void setArea(RectF _area) {
        this.area.set(_area);
        updateArea();
    }

    private void updateArea() {
        width = (int) area.getWidth();
        height = (int) area.getHeight();
        if (rawQuad == null) rawQuad = new Quad();
        rawQuad.set(
                toTexturePosition(0, 0),
                toTexturePosition(getWidth(), 0),
                toTexturePosition(0, getHeight()),
                toTexturePosition(getWidth(), getHeight()));
    }

    public void resize(float x, float y, float w, float h) {
        area.setXYWH(x, y, w, h);
        updateArea();
    }

    public void resize(float w, float h) {
        resize(area.getX1(), area.getY1(), w, h);
    }

    @Override
    public IQuad getRawQuad() {

        return rawQuad;
    }

    @Override
    public Vec2 toTexturePosition(float x, float y) {
        return texture.toTexturePosition(area.getX1() + x, area.getY1() + y);
    }

    @Override
    public GLTexture getTexture() {
        return texture;
    }

    @Override
    public int getTextureId() {
        return getTexture().getTextureId();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
