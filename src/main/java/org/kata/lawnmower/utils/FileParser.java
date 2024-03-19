package org.kata.lawnmower.utils;

import org.kata.lawnmower.enums.Orientation;
import org.kata.lawnmower.models.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    private static final String LINE_SEPARATOR = " ";

    public static List<MowerInput> parseInputFile(String filePath) throws IOException {
        String absoluteFilePath = getAbsoluteFilePath(filePath);
        List<String> lines = Files.readAllLines(Paths.get(absoluteFilePath));
        if (lines.isEmpty()) {
            return Collections.emptyList();
        }
        // Parse lawn dimensions
        String[] lawnDimensions = lines.get(0).split(LINE_SEPARATOR);
        int lawnWidth = Integer.parseInt(lawnDimensions[0]);
        int lawnHeight = Integer.parseInt(lawnDimensions[1]);

        // Parse mower inputs
        return parseMowerInputs(lines.subList(1, lines.size()), lawnWidth, lawnHeight);
    }

    private static List<MowerInput> parseMowerInputs(List<String> lines, int lawnWidth, int lawnHeight) {
        List<MowerInput> mowerInputs = new ArrayList<>();
        for (int i = 0; i < lines.size(); i += 2) {
            String positionLine = lines.get(i);
            String instructionsLine = lines.get(i + 1);
            mowerInputs.add(parseMowerInput(positionLine, instructionsLine, lawnWidth, lawnHeight));
        }
        return mowerInputs;
    }

    private static MowerInput parseMowerInput(String positionLine, String instructionsLine, int lawnWidth, int lawnHeight) {
        String[] mowerPosition = positionLine.split(LINE_SEPARATOR);
        int x = Integer.parseInt(mowerPosition[0]);
        int y = Integer.parseInt(mowerPosition[1]);
        String instructions = instructionsLine.trim();

        if (!isValidPosition(x, y, lawnWidth, lawnHeight)) {
            throw new IllegalArgumentException("Invalid initial position for mower: " + x + ", " + y);
        }
        Orientation orientation = Orientation.valueOf(mowerPosition[2]);

        RotationStrategy rotationStrategy = new DefaultRotationStrategy();
        Mower mower = new Mower(new Position(x, y, orientation), rotationStrategy);

        return new MowerInput(mower, instructions, lawnWidth, lawnHeight);
    }

    private static boolean isValidPosition(int x, int y, int lawnWidth, int lawnHeight) {
        return x >= 0 && x <= lawnWidth && y >= 0 && y <= lawnHeight;
    }

    private static String getAbsoluteFilePath(String filePath) {
        URL res = FileParser.class.getClassLoader().getResource(filePath);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return file.getAbsolutePath();
    }
}
