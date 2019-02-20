package org.firstinspires.ftc.teamcode.controls;

@FunctionalInterface
public interface PostSchemePreScaleOperation<Post> {
    Post apply(Post pre);
}
