package org.firstinspires.ftc.teamcode.driversetcontrols;

@FunctionalInterface
public interface PostSchemePreScaleOperation<Post> {
    Post apply(Post pre);
}
