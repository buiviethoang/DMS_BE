package com.thesis.dms.common.enums;

/**
 * Phan quyen cua nguoi dung <br>
 * 1: admin <br>
 * 2: sale admin <br>
 * 3: distributor <br>
 * 4: asm <br>
 * 5: supervisor <br>
 * 6: seller <br>
 */
public enum UserType {

    ADMIN(1),
    USER(2),
    SALE_ADMIN(3),
    DISTRIBUTOR(4),
    ASM(5),
    SUPERVISOR(6),
    SELLER(7);

    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserType getEnumById(Integer id) {
        for (UserType position : values()) {
            if (position.getValue() == id) {
                return position;
            }
        }
        return null;
    }
}
