package com.gablalib.pokedexcore.models.move

enum class MoveFlag(val flag: String) {
    PHYSICAL_CONTACT("physical_contact"),
    SOUND_TYPE("sound_type"),
    PUNCH_TYPE("punch_type"),
    SNATCHABLE("snatchable"),
    Z_MOVE("z_move"),
    DEFROSTS_WHEN_USED("defrosts_when_used"),
    REFLECTED_BY_MAGIC_COAT_MAGIC_BOUNCE("reflected_by_magic_coat_magic_bounce"),
    BLOCKED_BY_PROTECT_DETECT("blocked_by_protect_detect"),
    COPYABLE_BY_MIRROR_MOVE("copyable_by_mirror_move")
}