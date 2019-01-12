package org.firstinspires.ftc.teamcode.driversetcontrols;

@FunctionalInterface
public interface PostScaleOperation<Post> {
    Post apply(Post pre);
}
