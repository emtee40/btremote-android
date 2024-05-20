package com.atharok.btremote.domain.entity.keyboard.layout

import com.atharok.btremote.domain.entity.keyboard.KeyboardKey

class RUKeyboardLayout: KeyboardLayout() {

    protected override val keyboardKeys: Map<Char, ByteArray> by lazy {
        mapOf(
            ' ' to KEYBOARD_KEY_SPACE_BAR,

            'ё' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_00.byte),
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
            '-' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_11.byte),
            '=' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_12.byte),

            'й' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_00.byte),
            'ц' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_01.byte),
            'у' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_02.byte),
            'к' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_03.byte),
            'е' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_04.byte),
            'н' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_05.byte),
            'г' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_06.byte),
            'ш' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_07.byte),
            'щ' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_08.byte),
            'з' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_09.byte),
            'х' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_10.byte),
            'ъ' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_11.byte),

            'ф' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_00.byte),
            'ы' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_01.byte),
            'в' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_02.byte),
            'а' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_03.byte),
            'п' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_04.byte),
            'р' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_05.byte),
            'о' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_06.byte),
            'л' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_07.byte),
            'д' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_08.byte),
            'ж' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_09.byte),
            'э' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_10.byte),
            '\\' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_11.byte),

            'я' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_01.byte),
            'ч' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_02.byte),
            'с' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_03.byte),
            'м' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_04.byte),
            'и' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_05.byte),
            'т' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_06.byte),
            'ь' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_07.byte),
            'б' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_08.byte),
            'ю' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_09.byte),
            '.' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_10.byte),

            // ---- Shift ----

            'Ё' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_00.byte),
            '!' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_01.byte),
            '"' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_02.byte),
            '№' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_03.byte),
            ';' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_04.byte),
            '%' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_05.byte),
            ':' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_06.byte),
            '?' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_07.byte),
            '*' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_08.byte),
            '(' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_09.byte),
            ')' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_10.byte),
            '_' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_11.byte),
            '+' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_12.byte),

            'Й' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_00.byte),
            'Ц' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_01.byte),
            'У' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_02.byte),
            'К' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_03.byte),
            'Е' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_04.byte),
            'Н' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_05.byte),
            'Г' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_06.byte),
            'Ш' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_07.byte),
            'Щ' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_08.byte),
            'З' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_09.byte),
            'Х' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_10.byte),
            'Ъ' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_11.byte),

            'Ф' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_00.byte),
            'Ы' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_01.byte),
            'В' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_02.byte),
            'А' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_03.byte),
            'П' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_04.byte),
            'Р' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_05.byte),
            'О' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_06.byte),
            'Л' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_07.byte),
            'Д' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_08.byte),
            'Ж' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_09.byte),
            'Э' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_10.byte),
            '/' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_11.byte),

            'Я' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_01.byte),
            'Ч' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_02.byte),
            'С' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_03.byte),
            'М' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_04.byte),
            'И' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_05.byte),
            'Т' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_06.byte),
            'Ь' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_07.byte),
            'Б' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_08.byte),
            'Ю' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_09.byte),
            ',' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_10.byte),

            // ---- Alt Gr ----

            '`' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_00.byte),
            '@' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_02.byte),
            '#' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_03.byte),
            '$' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_04.byte),
            '^' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_06.byte),
            '&' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_07.byte),

            'q' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_00.byte),
            'w' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_01.byte),
            'e' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_02.byte),
            'r' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_03.byte),
            't' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_04.byte),
            'y' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_05.byte),
            'u' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_06.byte),
            'i' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_07.byte),
            'o' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_08.byte),
            'p' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_09.byte),
            '[' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_10.byte),
            ']' to byteArrayOf(0x40, KeyboardKey.ROW_2_KEY_11.byte),

            'a' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_00.byte),
            's' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_01.byte),
            'd' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_02.byte),
            'f' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_03.byte),
            'g' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_04.byte),
            'h' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_05.byte),
            'j' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_06.byte),
            'k' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_07.byte),
            'l' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_08.byte),
            '\'' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_10.byte),
            '|' to byteArrayOf(0x40, KeyboardKey.ROW_3_KEY_11.byte),

            'z' to byteArrayOf(0x40, KeyboardKey.ROW_4_KEY_01.byte),
            'x' to byteArrayOf(0x40, KeyboardKey.ROW_4_KEY_02.byte),
            'c' to byteArrayOf(0x40, KeyboardKey.ROW_4_KEY_03.byte),
            'v' to byteArrayOf(0x40, KeyboardKey.ROW_4_KEY_04.byte),
            'b' to byteArrayOf(0x40, KeyboardKey.ROW_4_KEY_05.byte),
            'n' to byteArrayOf(0x40, KeyboardKey.ROW_4_KEY_06.byte),
            'm' to byteArrayOf(0x40, KeyboardKey.ROW_4_KEY_07.byte),

            // ---- Shift + Alt Gr ----

            '~' to byteArrayOf(0x42, KeyboardKey.ROW_1_KEY_00.byte),

            'Q' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_00.byte),
            'W' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_01.byte),
            'E' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_02.byte),
            'R' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_03.byte),
            'T' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_04.byte),
            'Y' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_05.byte),
            'U' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_06.byte),
            'I' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_07.byte),
            'O' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_08.byte),
            'P' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_09.byte),
            '{' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_10.byte),
            '}' to byteArrayOf(0x42, KeyboardKey.ROW_2_KEY_11.byte),

            'A' to byteArrayOf(0x42, KeyboardKey.ROW_3_KEY_00.byte),
            'S' to byteArrayOf(0x42, KeyboardKey.ROW_3_KEY_01.byte),
            'D' to byteArrayOf(0x42, KeyboardKey.ROW_3_KEY_02.byte),
            'F' to byteArrayOf(0x42, KeyboardKey.ROW_3_KEY_03.byte),
            'G' to byteArrayOf(0x42, KeyboardKey.ROW_3_KEY_04.byte),
            'H' to byteArrayOf(0x42, KeyboardKey.ROW_3_KEY_05.byte),
            'J' to byteArrayOf(0x42, KeyboardKey.ROW_3_KEY_06.byte),
            'K' to byteArrayOf(0x42, KeyboardKey.ROW_3_KEY_07.byte),
            'L' to byteArrayOf(0x42, KeyboardKey.ROW_3_KEY_08.byte),

            'Z' to byteArrayOf(0x42, KeyboardKey.ROW_4_KEY_01.byte),
            'X' to byteArrayOf(0x42, KeyboardKey.ROW_4_KEY_02.byte),
            'C' to byteArrayOf(0x42, KeyboardKey.ROW_4_KEY_03.byte),
            'V' to byteArrayOf(0x42, KeyboardKey.ROW_4_KEY_04.byte),
            'B' to byteArrayOf(0x42, KeyboardKey.ROW_4_KEY_05.byte),
            'N' to byteArrayOf(0x42, KeyboardKey.ROW_4_KEY_06.byte),
            'M' to byteArrayOf(0x42, KeyboardKey.ROW_4_KEY_07.byte),
            '<' to byteArrayOf(0x42, KeyboardKey.ROW_4_KEY_08.byte),
            '>' to byteArrayOf(0x42, KeyboardKey.ROW_4_KEY_09.byte)
        )
    }

    protected override val extraKeys: Map<Char, ByteArray> by lazy {
        mapOf(
            '¹' to (keyboardKeys['1'] ?: KEYBOARD_KEY_NONE),
            '²' to (keyboardKeys['2'] ?: KEYBOARD_KEY_NONE),
            '³' to (keyboardKeys['3'] ?: KEYBOARD_KEY_NONE),
            '«' to (keyboardKeys['"'] ?: KEYBOARD_KEY_NONE),
            '»' to (keyboardKeys['"'] ?: KEYBOARD_KEY_NONE),
            'µ' to (keyboardKeys['u'] ?: KEYBOARD_KEY_NONE),
            '¥' to (keyboardKeys['Y'] ?: KEYBOARD_KEY_NONE),
            '€' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            '¢' to (keyboardKeys['c'] ?: KEYBOARD_KEY_NONE),

            'À' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Á' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Â' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Ã' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Ä' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Å' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Ą' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Æ' to (keyboardKeys['A'] ?: KEYBOARD_KEY_NONE),
            'Ç' to (keyboardKeys['C'] ?: KEYBOARD_KEY_NONE),
            'Č' to (keyboardKeys['C'] ?: KEYBOARD_KEY_NONE),
            'Ć' to (keyboardKeys['C'] ?: KEYBOARD_KEY_NONE),
            'È' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'É' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'Ê' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'Ë' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'Ě' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'Ę' to (keyboardKeys['E'] ?: KEYBOARD_KEY_NONE),
            'Ì' to (keyboardKeys['I'] ?: KEYBOARD_KEY_NONE),
            'Í' to (keyboardKeys['I'] ?: KEYBOARD_KEY_NONE),
            'Î' to (keyboardKeys['I'] ?: KEYBOARD_KEY_NONE),
            'Ï' to (keyboardKeys['I'] ?: KEYBOARD_KEY_NONE),
            'Ł' to (keyboardKeys['L'] ?: KEYBOARD_KEY_NONE),
            'Ñ' to (keyboardKeys['N'] ?: KEYBOARD_KEY_NONE),
            'Ń' to (keyboardKeys['N'] ?: KEYBOARD_KEY_NONE),
            'Ò' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            'Ó' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            'Ô' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            'Õ' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            'Ö' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            '×' to (keyboardKeys['x'] ?: KEYBOARD_KEY_NONE),
            'Ø' to (keyboardKeys['O'] ?: KEYBOARD_KEY_NONE),
            'Ř' to (keyboardKeys['R'] ?: KEYBOARD_KEY_NONE),
            'Š' to (keyboardKeys['S'] ?: KEYBOARD_KEY_NONE),
            'Ś' to (keyboardKeys['S'] ?: KEYBOARD_KEY_NONE),
            'Ù' to (keyboardKeys['U'] ?: KEYBOARD_KEY_NONE),
            'Ú' to (keyboardKeys['U'] ?: KEYBOARD_KEY_NONE),
            'Û' to (keyboardKeys['U'] ?: KEYBOARD_KEY_NONE),
            'Ü' to (keyboardKeys['U'] ?: KEYBOARD_KEY_NONE),
            'Ẃ' to (keyboardKeys['W'] ?: KEYBOARD_KEY_NONE),
            'Ý' to (keyboardKeys['Y'] ?: KEYBOARD_KEY_NONE),
            'Ž' to (keyboardKeys['Z'] ?: KEYBOARD_KEY_NONE),
            'Ż' to (keyboardKeys['Z'] ?: KEYBOARD_KEY_NONE),
            'Ź' to (keyboardKeys['Z'] ?: KEYBOARD_KEY_NONE),

            'à' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'á' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'â' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'ã' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'ä' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'å' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'ą' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'æ' to (keyboardKeys['a'] ?: KEYBOARD_KEY_NONE),
            'ç' to (keyboardKeys['c'] ?: KEYBOARD_KEY_NONE),
            'č' to (keyboardKeys['c'] ?: KEYBOARD_KEY_NONE),
            'ć' to (keyboardKeys['c'] ?: KEYBOARD_KEY_NONE),
            'è' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'é' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'ê' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'ë' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'ě' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'ę' to (keyboardKeys['e'] ?: KEYBOARD_KEY_NONE),
            'ì' to (keyboardKeys['i'] ?: KEYBOARD_KEY_NONE),
            'í' to (keyboardKeys['i'] ?: KEYBOARD_KEY_NONE),
            'î' to (keyboardKeys['i'] ?: KEYBOARD_KEY_NONE),
            'ï' to (keyboardKeys['i'] ?: KEYBOARD_KEY_NONE),
            'ł' to (keyboardKeys['l'] ?: KEYBOARD_KEY_NONE),
            'ñ' to (keyboardKeys['n'] ?: KEYBOARD_KEY_NONE),
            'ń' to (keyboardKeys['n'] ?: KEYBOARD_KEY_NONE),
            'ò' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            'ó' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            'ô' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            'õ' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            'ö' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            '÷' to (keyboardKeys['/'] ?: KEYBOARD_KEY_NONE),
            'ø' to (keyboardKeys['o'] ?: KEYBOARD_KEY_NONE),
            'ř' to (keyboardKeys['r'] ?: KEYBOARD_KEY_NONE),
            'š' to (keyboardKeys['s'] ?: KEYBOARD_KEY_NONE),
            'ś' to (keyboardKeys['s'] ?: KEYBOARD_KEY_NONE),
            'ù' to (keyboardKeys['u'] ?: KEYBOARD_KEY_NONE),
            'ú' to (keyboardKeys['u'] ?: KEYBOARD_KEY_NONE),
            'û' to (keyboardKeys['u'] ?: KEYBOARD_KEY_NONE),
            'ü' to (keyboardKeys['u'] ?: KEYBOARD_KEY_NONE),
            'ẃ' to (keyboardKeys['w'] ?: KEYBOARD_KEY_NONE),
            'ý' to (keyboardKeys['y'] ?: KEYBOARD_KEY_NONE),
            'ÿ' to (keyboardKeys['y'] ?: KEYBOARD_KEY_NONE),
            'ž' to (keyboardKeys['z'] ?: KEYBOARD_KEY_NONE),
            'ż' to (keyboardKeys['z'] ?: KEYBOARD_KEY_NONE),
            'ź' to (keyboardKeys['z'] ?: KEYBOARD_KEY_NONE)
        )
    }
}