package org.firstinspires.ftc.teamcode.controls;

@FunctionalInterface
public interface PostScaleOperation<Post> {
    Post apply(Post pre);
}
