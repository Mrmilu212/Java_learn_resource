package TSP;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TSPGeneticAlgorithm {

   public static final int CITY_COUNT = 6;
   public static final int POPULATION_SIZE = 100;
   public static final int NUM_GENERATIONS = 500;
   public static final double MUTATION_RATE = 0.015;

   public static final List<City> cities = new ArrayList<>();
   public static final Random random = new Random();

    static {
        // 用随机数为城市设定位置
        for (int i = 0; i < CITY_COUNT; i++) {
            cities.add(new City(random.nextInt(200), random.nextInt(200)));
        }
    }

    public static void main(String[] args) {
        List<Route> population = initializePopulation();

        for (int generation = 0; generation < NUM_GENERATIONS; generation++) {
            Collections.sort(population, Comparator.comparingDouble(Route::getFitness));
            population = evolvePopulation(population);
        }

        Route bestRoute = population.get(0);
        System.out.println("最佳路线: " + bestRoute);

        plotRoute(bestRoute);
    }

    private static List<Route> initializePopulation() {
        List<Route> population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new Route(cities));
        }
        return population;
    }

    private static List<Route> evolvePopulation(List<Route> population) {
        List<Route> newPopulation = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            Route parent1 = selectParent(population);
            Route parent2 = selectParent(population);

            Route child = crossover(parent1, parent2);
            mutate(child);
            newPopulation.add(child);
        }

        return newPopulation;
    }

    private static Route selectParent(List<Route> population) {
        // 竞争式选择
        List<Route> tournament = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tournament.add(population.get(random.nextInt(POPULATION_SIZE)));
        }
        Collections.sort(tournament, Comparator.comparingDouble(Route::getFitness));
        return tournament.get(0);
    }

    private static Route crossover(Route parent1, Route parent2) {
        Route child = new Route();

        int startPos = random.nextInt(CITY_COUNT);
        int endPos = random.nextInt(CITY_COUNT);

        for (int i = 0; i < CITY_COUNT; i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child.getCities().set(i, parent1.getCities().get(i));
            } else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.getCities().set(i, parent1.getCities().get(i));
                }
            }
        }

        for (int i = 0; i < CITY_COUNT; i++) {
            if (!child.getCities().contains(parent2.getCities().get(i))) {
                for (int j = 0; j < CITY_COUNT; j++) {
                    if (child.getCities().get(j) == null) {
                        child.getCities().set(j, parent2.getCities().get(i));
                        break;
                    }
                }
            }
        }

        return child;
    }

    private static void mutate(Route route) {
        for (int i = 0; i < CITY_COUNT; i++) {
            if (Math.random() < MUTATION_RATE) {
                int j = random.nextInt(CITY_COUNT);

                City city1 = route.getCities().get(i);
                City city2 = route.getCities().get(j);

                route.getCities().set(i, city2);
                route.getCities().set(j, city1);
            }
        }
    }

    private static void plotRoute(Route route) {
        XYChart chart = new XYChartBuilder().width(800).height(600).title("TSP Solution").xAxisTitle("X").yAxisTitle("Y").build();
        double[] xData = new double[CITY_COUNT + 1];
        double[] yData = new double[CITY_COUNT + 1];

        for (int i = 0; i < CITY_COUNT; i++) {
            xData[i] = route.getCities().get(i).getX();
            yData[i] = route.getCities().get(i).getY();
        }
        xData[CITY_COUNT] = route.getCities().get(0).getX();
        yData[CITY_COUNT] = route.getCities().get(0).getY();

        chart.addSeries("Best Route", xData, yData);

        new SwingWrapper<>(chart).displayChart();
    }
}
//定义城市类
class City {
    private final int x;
    private final int y;

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
//定义路线
class Route {
    private final List<City> cities;
    private double fitness;

    public Route(List<City> cities) {
        this.cities = new ArrayList<>(cities);
        Collections.shuffle(this.cities);
        this.fitness = calculateFitness();
    }

    public Route() {
        this.cities = new ArrayList<>(Collections.nCopies(TSPGeneticAlgorithm.CITY_COUNT, null));
        this.fitness = Double.MAX_VALUE;
    }

    public List<City> getCities() {
        return cities;
    }

    public double getFitness() {
        if (fitness == Double.MAX_VALUE) {
            fitness = calculateFitness();
        }
        return fitness;
    }

    private double calculateFitness() {
        double totalDistance = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            totalDistance += distance(cities.get(i), cities.get(i + 1));
        }
        totalDistance += distance(cities.get(cities.size() - 1), cities.get(0));
        return totalDistance;
    }

    private double distance(City city1, City city2) {
        int xDistance = Math.abs(city1.getX() - city2.getX());
        int yDistance = Math.abs(city1.getY() - city2.getY());
        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }

    @Override
    public String toString() {
        return "Route{" + "cities=" + cities + ", fitness=" + fitness + '}';
    }
}
