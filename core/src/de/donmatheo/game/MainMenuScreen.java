package de.donmatheo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by donmatheo on 19.11.2014.
 */
public class MainMenuScreen implements Screen {

    final UnstableRelations game;
    private final Stage stage;
    private Music music;
    OrthographicCamera camera;

    public MainMenuScreen(final UnstableRelations game) {
        music = Gdx.audio.newMusic(Gdx.files.internal("music/start-screen.mp3"));

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        // setup viewport and stage
        FitViewport viewport = new FitViewport(800, 480);
        viewport.setCamera(camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(UnstableRelations.DARKGREY.r, UnstableRelations.DARKGREY.g, UnstableRelations.DARKGREY.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.mainfont.draw(game.batch, "Welcome to", 330, camera.viewportHeight / 2 + 130);
        game.titlefont.draw(game.batch, "Magical", 160, camera.viewportHeight / 2 + 60);
        game.titlefont.draw(game.batch, "Dots", 450, camera.viewportHeight / 2 + 60);
        game.mainfont.draw(game.batch, "Tap anywhere to begin!", 250, camera.viewportHeight/2 - 50);

        game.batch.end();

        stage.act(delta);
        stage.draw();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        music.setLooping(true);
        music.play();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        music.dispose();
    }
}
