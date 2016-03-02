package com.wakaleo.gameoflife.webtests.beans;

import com.wakaleo.gameoflife.domain.Universe;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * This class rocks.
 *
 * @author johnsmart
 */
@Named
@SessionScoped
public class GameBean implements Serializable {

    private int rows = 3;
    private int columns = 3;
    private Universe universe;
    private Random randomGenerator = new Random();

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public String getAppVersion() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/system.properties");
        Properties prop = new Properties();
        String appVersion = "";
        if (inputStream != null) {
            try {
                prop.load(inputStream);
                appVersion = prop.getProperty("app.version");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return appVersion;
    }

    public String newGame() {
        universe = new Universe();
        thinkABit(250);
        return "game/edit";
    }

    public String firstGeneration() {
        ExternalContext extcontext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) extcontext.getRequest();
        int rows = Integer.parseInt(request.getParameter("rows"));
        int columns = Integer.parseInt(request.getParameter("columns"));
        return firstGeneration(rows, columns, request);
    }

    public String firstGeneration(int rows, int columns, HttpServletRequest request) {
        if (request != null) {
            universe = universeInstanciatedFromClickedCells(rows, columns, request);
            thinkABit(200);
            this.columns = universe.getCells()[0].length;
            this.rows = universe.getCells().length;
        }
        return "game/show";
    }

    public String nextGeneration() {
        ExternalContext extcontext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) extcontext.getRequest();
        int rows = Integer.parseInt(request.getParameter("rows"));
        int columns = Integer.parseInt(request.getParameter("columns"));
        return nextGeneration(rows, columns, request);
       
    }

    public String nextGeneration(int rows, int columns, HttpServletRequest request) {
        universe = universeInstanciatedFromClickedCells(rows, columns,
                request);
        universe.createNextGeneration();
        thinkABit(250);
        this.columns = universe.getCells()[0].length;
        this.rows = universe.getCells().length;
        return "game/show";
    }

    private void thinkABit(final int max) {
        int thinkingTime = getRandomGenerator().nextInt(max / 4);
        try {
            Thread.currentThread().sleep(thinkingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Universe universeInstanciatedByDimensions(final int rows, final int columns) {
        Universe universe = new Universe(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                universe.setDeadCellAt(row, column);
            }
        }
        return universe;
    }

    private Universe universeInstanciatedFromClickedCells(final int rows,
            final int columns,
            final HttpServletRequest request) {
        Universe universe = universeInstanciatedByDimensions(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (cellWasClickedAt(row, column, request)) {
                    universe.setLiveCellAt(row, column);
                }
            }
        }
        return universe;
    }

    private boolean cellWasClickedAt(final int row,
            final int column,
            final HttpServletRequest request) {
        String cellName = "cell_" + row + "_" + column;
        return (request.getParameter(cellName) != null);
    }

    private Random getRandomGenerator() {
        return randomGenerator;
    }
}
