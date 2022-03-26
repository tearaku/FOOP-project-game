import collisionhandlers.JetBulletCollisionHandler;
import collisionhandlers.PickupCollisionHandler;
import jet.*;
import jet.attackStates.SizeUpAttack;
import jet.attackStates.DamageUpAttack;
import jet.attackStates.NormalAttacking;
import jet.attackStates.ShotgunAttack;
import media.BackgroundMusic;
import model.CompositeCollisionHandler;
import controller.Game;
import model.Direction;
import model.HealthPointSprite;
import model.World;
import pickup.*;
import views.GameView;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import static media.AudioPlayer.addAudioByFilePath;

/**
 * Demo route: Main, GameView, Game, GameLoop, World, Sprite, Knight, FiniteStateMachine
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        addAudioByFilePath(BackgroundMusic.BM, new File("assets/audio/background.wav"));
        addAudioByFilePath(NormalAttacking.SHOOT, new File("assets/audio/shoot.wav"));
        addAudioByFilePath(Walking.AUDIO_STEP1, new File("assets/audio/step1.wav"));
        addAudioByFilePath(Walking.AUDIO_STEP2, new File("assets/audio/step2.wav"));
        addAudioByFilePath(HealthPointSprite.AUDIO_DIE, new File("assets/audio/die.wav"));
        addAudioByFilePath(HealingItem.HEALING, new File("assets/audio/healing.wav"));
        addAudioByFilePath(DamageUpAttack.INCREASED_DMG, new File("assets/audio/increased_dmg.wav"));
        addAudioByFilePath(SizeUpAttack.LARGER, new File("assets/audio/larger.wav"));
        addAudioByFilePath(ShotgunAttack.SHOTGUN, new File("assets/audio/shotgun.wav"));

        // Setup players
        Jet p1 = new Jet(new Point(GameView.WIDTH/2, 0), Direction.DOWN, 7);
        Jet p2 = new Jet(new Point(GameView.WIDTH/2, GameView.HEIGHT-100), Direction.UP, 7);
        // Setup collision handlers for world
        World world = new World(new CompositeCollisionHandler(Arrays.asList(
                new JetBulletCollisionHandler(),
                new PickupCollisionHandler()
        )), p1, p2);  // model
        Game game = new Game(world, p1, p2);  // controller
        GameView view = new GameView(game);  // view
        // Add item generator
        List<? extends PickupItem> availablePickups = Arrays.asList(
                new HealingItem(new Point(0, 0)),
                new DamageUpItem(new Point(0, 0)),
                new BigBulletItem(new Point(0, 0)),
                new ShotgunItem(new Point(0, 0))
        );
        world.addSprite(new PickupGenerator(400, availablePickups));
        // Background music player
        BackgroundMusic bm = new BackgroundMusic();

        game.start();  // run the game and the game loop
        view.launch(); // launch the GUI
        bm.start(); // start background music
    }
}
