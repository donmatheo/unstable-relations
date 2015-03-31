package de.donmatheo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by donmatheo on 31.03.2015.
 */
public class Ending extends Actor {
    private final UnstableRelations game;
    private final ShapeRenderer renderer;
    OrthographicCamera camera;

    

    public Ending(UnstableRelations game, OrthographicCamera camera) {
        this.game = game;
        this.camera = camera;
        renderer = new ShapeRenderer();
    }

    public void draw(Batch batch, float parentAlpha) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(new Color(0, 0, 0, 0.4f));
        renderer.rect(0, 0, camera.viewportWidth, camera.viewportHeight);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        game.batch.begin();
        game.titlefont.draw(game.batch, "Congratulations", 80, camera.viewportHeight / 2 + 60);
        game.mainfont.draw(game.batch, "Tap anywhere to restart", 250, camera.viewportHeight / 2 - 180);
        game.batch.end();

    }

}
