package bullet;

import fsm.ImageRenderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class BulletImageRenderer implements ImageRenderer {
    protected Bullet bullet;

    public BulletImageRenderer(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public void render(Image image, Graphics g) {
        Rectangle range = bullet.getRange();
        Rectangle body = bullet.getBody();
        Dimension body_off = bullet.getBodyOffset();
        double angle = bullet.angle+90;
        double angleRadian = Math.toRadians(angle);
        BufferedImage buffered = (BufferedImage) image;
        AffineTransform at= AffineTransform.getTranslateInstance(range.x,range.y);
        at.rotate(Math.toRadians(angle));
        Graphics2D g2d =(Graphics2D) g;
        at.scale((double)range.width/buffered.getWidth(),(double)range.height/buffered.getHeight());
        g2d.drawImage(buffered,at,null);
        g.setColor(Color.RED);
        g.drawRect(range.x+ body_off.width, range.y+body_off.height, body.width, body.height);
        /*
        Rectangle range = bullet.getRange();
        Rectangle body = bullet.getBody();
        double angle = bullet.angle;
        BufferedImage buffered = (BufferedImage) image;
        AffineTransform at= AffineTransform.getTranslateInstance(range.x,range.y);
        at.rotate(Math.toRadians(angle+90),0,0);
        Graphics2D g2d =(Graphics2D) g;
        at.scale((double)range.width/buffered.getWidth(),(double)range.height/buffered.getHeight());
        g2d.drawImage(buffered,at,null);
        g.setColor(Color.RED);
        g.drawRect(body.x, body.y, body.width, body.height);
         */
    }
}
