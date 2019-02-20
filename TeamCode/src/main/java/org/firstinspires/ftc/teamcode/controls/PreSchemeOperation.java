package org.firstinspires.ftc.teamcode.controls;

@FunctionalInterface
public interface PreSchemeOperation<Post> {
    Post apply(Post pre);
}
