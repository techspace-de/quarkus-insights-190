package de.techspace.quarkus.code.stage;

import java.util.Set;

public record Extension(String id, String name, String shortName, Set<String> keywords) {
}
