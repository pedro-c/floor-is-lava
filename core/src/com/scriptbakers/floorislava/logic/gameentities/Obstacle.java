package com.scriptbakers.floorislava.logic.gameentities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import static com.scriptbakers.floorislava.Constants.CATEGORY_OBSTACLE;
import static com.scriptbakers.floorislava.Constants.MASK_OBSTACLE;
import static com.scriptbakers.floorislava.Constants.SCROLL_VELOCITY;
import static com.scriptbakers.floorislava.Constants.WORLD_WIDTH;


/**
 * Created by bernardo on 04-11-2016.
 */

public abstract class Obstacle {
    Body body;
    World world;
    Texture texture;

    public Obstacle(World world) {
        this.world = world;
    }

    protected void createBody(Shape shape, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = CATEGORY_OBSTACLE;
        fixtureDef.filter.maskBits = MASK_OBSTACLE;
        body.createFixture(fixtureDef);

        body.setLinearVelocity(0,SCROLL_VELOCITY);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Body getBody() {
        return body;
    }

    public void setTexture(Texture texture) {
        if(this.texture == null)
            this.texture = texture;
    }

    public abstract void draw(SpriteBatch batch);

    public abstract Vector2 getDimensions();
}
