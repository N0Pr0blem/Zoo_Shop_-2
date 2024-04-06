package org.example.service;

import com.ibm.icu.text.Transliterator;
import org.springframework.context.annotation.Bean;

public class PathGenerator {
    final String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";

    public String rusToLatin(String rus){
        Transliterator latin = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        String result = latin.transliterate(rus);
        return result.replace(' ','_');
    }
}
