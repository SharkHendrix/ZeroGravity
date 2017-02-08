package fr.sharkhendrix.zerogravity.client;

import java.util.Locale;

import lombok.Data;

@Data
public class Settings {
    private Locale locale = Locale.getDefault();
}
