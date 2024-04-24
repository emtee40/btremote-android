package com.atharok.btremote.domain.entity.keyboard.layout


import com.atharok.btremote.domain.entity.keyboard.KeyboardKey

class DEKeyboardLayout: KeyboardLayout() {

    protected override val keyboardKeys: Map<Char, ByteArray> by lazy {
        mapOf(
            ' ' to KEYBOARD_KEY_SPACE_BAR,

            '^' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_00.byte),
            '1' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_01.byte),
            '2' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_02.byte),
            '3' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_03.byte),
            '4' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_04.byte),
            '5' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_05.byte),
            '6' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_06.byte),
            '7' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_07.byte),
            '8' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_08.byte),
            '9' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_09.byte),
            '0' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_10.byte),
            'ß' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_11.byte),
            '´' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_12.byte),

            'q' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_00.byte),
            'w' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_01.byte),
            'e' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_02.byte),
            'r' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_03.byte),
            't' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_04.byte),
            'z' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_05.byte),
            'u' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_06.byte),
            'i' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_07.byte),
            'o' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_08.byte),
            'p' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_09.byte),
            'ü' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_10.byte),
            '+' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_11.byte),

            'a' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_00.byte),
            's' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_01.byte),
            'd' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_02.byte),
            'f' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_03.byte),
            'g' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_04.byte),
            'h' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_05.byte),
            'j' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_06.byte),
            'k' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_07.byte),
            'l' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_08.byte),
            'ö' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_09.byte),
            'ä' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_10.byte),
            '#' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_11.byte),

            '<' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_00.byte),
            'y' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_01.byte),
            'x' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_02.byte),
            'c' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_03.byte),
            'v' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_04.byte),
            'b' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_05.byte),
            'n' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_06.byte),
            'm' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_07.byte),
            ',' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_08.byte),
            '.' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_09.byte),
            '-' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_10.byte),

            // ---- Shift ----

            '°' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_00.byte),
            '!' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_01.byte),
            '"' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_02.byte),
            '§' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_03.byte),
            '$' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_04.byte),
            '%' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_05.byte),
            '&' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_06.byte),
            '/' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_07.byte),
            '(' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_08.byte),
            ')' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_09.byte),
            '=' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_10.byte),
            '?' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_11.byte),
            '`' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_12.byte),

            'Q' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_00.byte),
            'W' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_01.byte),
            'E' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_02.byte),
            'R' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_03.byte),
            'T' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_04.byte),
            'Z' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_05.byte),
            'U' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_06.byte),
            'I' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_07.byte),
            'O' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_08.byte),
            'P' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_09.byte),
            'Ü' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_10.byte),
            '*' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_11.byte),

            'A' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_00.byte),
            'S' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_01.byte),
            'D' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_02.byte),
            'F' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_03.byte),
            'G' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_04.byte),
            'H' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_05.byte),
            'J' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_06.byte),
            'K' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_07.byte),
            'L' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_08.byte),
            'Ö' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_09.byte),
            'Ä' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_10.byte),
            '\'' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_11.byte),

            '>' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_00.byte),
            'Y' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_01.byte),
            'X' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_02.byte),
            'C' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_03.byte),
            'V' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_04.byte),
            'B' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_05.byte),
            'N' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_06.byte),
            'M' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_07.byte),
            ';' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_08.byte),
            ':' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_09.byte),
            '_' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_10.byte),

            // ---- Alt Gr ----

            '²' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_02.byte),
            '³' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_03.byte),
            '{' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_07.byte),
            '[' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_08.byte),
            ']' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_09.byte),
            '}' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_10.byte),
            '\\' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_11.byte),

            '@' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_00.byte),
            '€' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_02.byte),
            '~' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_11.byte),

            '|' to byteArrayOf(0x40, KeyboardKey.ROW_4_KEY_00.byte),
            'µ' to byteArrayOf(0x40, KeyboardKey.ROW_4_KEY_07.byte),
        )
    }

    protected override val extraKeys: Map<Char, ByteArray> by lazy {
        mapOf(
            '¹' to (keyboardKeys['1'] ?: KEYBOARD_KEY_NONE),
            '«' to (keyboardKeys['"'] ?: KEYBOARD_KEY_NONE),
            '»' to (keyboardKeys['"'] ?: KEYBOARD_KEY_NONE),
            '¥' to (keyboardKeys['Y'] ?: KEYBOARD_KEY_NONE),
            '¢' to (keyboardKeys['c'] ?: KEYBOARD_KEY_NONE),

            'À' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Á' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Â' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Ã' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Å' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Æ' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Ç' to (keyboardKeys['C'] ?: KEYBOARD_KEY_NONE),
            'È' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'É' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'Ê' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'Ë' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'Ì' to (keyboardKeys['I'] ?: KEYBOARD_KEY_NONE),
            'Í' to (keyboardKeys['I'] ?: KEYBOARD_KEY_NONE),
            'Î' to (keyboardKeys['I'] ?: KEYBOARD_KEY_NONE),
            'Ï' to (keyboardKeys['I'] ?: KEYBOARD_KEY_NONE),
            'Ñ' to (keyboardKeys['N'] ?: KEYBOARD_KEY_NONE),
            'Ò' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            'Ó' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            'Ô' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            'Õ' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            '×' to (keyboardKeys['x'] ?: KEYBOARD_KEY_NONE),
            'Ø' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            'Ù' to (keyboardKeys['U'] ?: KEYBOARD_KEY_NONE),
            'Ú' to (keyboardKeys['U'] ?: KEYBOARD_KEY_NONE),
            'Û' to (keyboardKeys['U'] ?: KEYBOARD_KEY_NONE),
            'Ẃ' to (keyboardKeys['W'] ?: KEYBOARD_KEY_NONE),
            'Ý' to (keyboardKeys['Y'] ?: KEYBOARD_KEY_NONE),

            'à' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'á' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'â' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'ã' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'å' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'æ' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'ç' to (keyboardKeys['c'] ?: KEYBOARD_KEY_NONE),
            'è' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'é' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'ê' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'ë' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'ì' to (keyboardKeys['i'] ?: KEYBOARD_KEY_NONE),
            'í' to (keyboardKeys['i'] ?: KEYBOARD_KEY_NONE),
            'î' to (keyboardKeys['i'] ?: KEYBOARD_KEY_NONE),
            'ï' to (keyboardKeys['i'] ?: KEYBOARD_KEY_NONE),
            'ñ' to (keyboardKeys['n'] ?: KEYBOARD_KEY_NONE),
            'ò' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            'ó' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            'ô' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            'õ' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            '÷' to (keyboardKeys['/'] ?: KEYBOARD_KEY_NONE),
            'ø' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            'ù' to (keyboardKeys['u'] ?: KEYBOARD_KEY_NONE),
            'ú' to (keyboardKeys['u'] ?: KEYBOARD_KEY_NONE),
            'û' to (keyboardKeys['u'] ?: KEYBOARD_KEY_NONE),
            'ẃ' to (keyboardKeys['w'] ?: KEYBOARD_KEY_NONE),
            'ý' to (keyboardKeys['y'] ?: KEYBOARD_KEY_NONE),
            'ÿ' to (keyboardKeys['y'] ?: KEYBOARD_KEY_NONE)
        )
    }
}