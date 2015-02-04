package de.donmatheo.game;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by donmatheo on 18.10.2014.
 */
public class Dot extends Actor {

    public static int DEFAULTRADIUS = 35;
    private final PointLight pointLight;
    private Circle circleBounds;
    private Relation relation1;
    private Relation relation2;

    private ShapeRenderer renderer;
    private boolean touched;
    private Vector2 center;

    public Dot(RayHandler rayHandler) {
        pointLight = new PointLight(rayHandler, 200, Color.ORANGE, 250, getX(), getY());
        pointLight.setActive(false);
        renderer = new ShapeRenderer();
        circleBounds = new Circle(DEFAULTRADIUS, DEFAULTRADIUS, DEFAULTRADIUS);
        center = new Vector2();
        setBounds(0, 0, DEFAULTRADIUS * 2, DEFAULTRADIUS * 2);
        setOrigin(0,0);
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(UnstableRelations.YELLOW);
        if (hasIsoscelesRelations()) {
            renderer.circle( center.x, center.y, DEFAULTRADIUS + 4);
        }

        if (hasIsoscelesRelations()) {
            renderer.setColor(UnstableRelations.LIGHTBLUE);
        } else {
            renderer.setColor(UnstableRelations.BLUE);
        }
        renderer.circle(center.x, center.y, DEFAULTRADIUS);
        renderer.end();
        batch.begin();
    }

    public boolean hasIsoscelesRelations() {
        double dist1 = distance(relation1.getTarget());
        double dist2 = distance(relation2.getTarget());

        double median = (dist1 + dist2) / 2;
        double absoluteDiff = Math.abs(dist1 - dist2);
        if (absoluteDiff / median < 0.1) {
            pointLight.setActive(true);
            return true;
        } else {
            pointLight.setActive(false);
            return false;
        }
    }

    private double distance(Dot neighbour) {
        double dx = getX() - neighbour.getX();         //horizontal difference
        double dy = getY() - neighbour.getY();         //vertical difference
        return Math.sqrt(dx * dx + dy * dy);           //distance using Pythagoras theorem
    }


    public Actor hit(float x, float y, boolean touchable){
        if(!this.isVisible() || this.getTouchable() == Touchable.disabled)
            return null;

        if (circleBounds.contains(x,y))
            return this;

        return null;
    }


    public boolean isTouched() {
        return touched;
    }

    public void updatePosition(float x, float y){
        setPosition(x, y);
        center.set(x + getWidth() / 2, y + getHeight()/2);
        pointLight.setPosition(center);
    }
    public Relation getRelation1() {
        return relation1;
    }

    public void setRelation1(Relation relation1) {
        this.relation1 = relation1;
    }

    public Relation getRelation2() {
        return relation2;
    }

    public void setRelation2(Relation relation2) {
        this.relation2 = relation2;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public Vector2 getCenter() {
        return center;
    }
}
