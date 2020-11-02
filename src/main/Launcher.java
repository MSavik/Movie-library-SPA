package main;

import static spark.Spark.*;
import java.util.*;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Launcher {
    public static void main(String[] args) {

        staticFiles.location("/public");
        port(5000);
        String path = "movies.json";
        HashMap<String, Object> fields = new HashMap<>();

        /* *************************** *
         * Main page, lists with data  *
         * *************************** */
        get("/",(request,response) -> {
            fields.put("moviesData", Data.readFromJson(path));
            
            ArrayList<String> titleList = new ArrayList<>();
            ArrayList<String> genreList = new ArrayList<>();
            ArrayList<Integer> yearOfReleaseList = new ArrayList<>();
            ArrayList<String> starringList = new ArrayList<>();
            ArrayList<String> directedByList = new ArrayList<>();
            ArrayList<String> producedByList = new ArrayList<>();
            ArrayList<String> writtenByList = new ArrayList<>();
            ArrayList<String> productionCompanyList = new ArrayList<>();
            ArrayList<String> countryList = new ArrayList<>();
            ArrayList<String> languageList = new ArrayList<>();

            for (Movie movie : Data.readFromJson(path)){
                if(!titleList.contains(movie.getTitle())){
                    titleList.add(movie.getTitle());
                    titleList.sort(String::compareToIgnoreCase);
                }

                if(!genreList.contains(movie.getGenre())){
                    genreList.add(movie.getGenre());
                    genreList.sort(String::compareToIgnoreCase);
                }

                if(!yearOfReleaseList.contains(movie.getYearOfRelease())){
                    yearOfReleaseList.add(movie.getYearOfRelease());
                    Collections.sort(yearOfReleaseList);
                }

                for (int i = 0; i < movie.getStarring().size(); i++){
                    if(!starringList.contains(movie.getStarring().get(i))){
                        starringList.add(movie.getStarring().get(i));
                        starringList.sort(String::compareToIgnoreCase);
                    }
                }

                for (int i = 0; i < movie.getDirectedBy().size(); i++){
                    if(!directedByList.contains(movie.getDirectedBy().get(i))){
                        directedByList.add(movie.getDirectedBy().get(i));
                        directedByList.sort(String::compareToIgnoreCase);
                    }
                }

                for (int i = 0; i < movie.getProducedBy().size(); i++){
                    if(!producedByList.contains(movie.getProducedBy().get(i))){
                        producedByList.add(movie.getProducedBy().get(i));
                        producedByList.sort(String::compareToIgnoreCase);
                    }
                }

                for (int i = 0; i < movie.getWrittenBy().size(); i++){
                    if(!writtenByList.contains(movie.getWrittenBy().get(i))){
                        writtenByList.add(movie.getWrittenBy().get(i));
                        writtenByList.sort(String::compareToIgnoreCase);
                    }
                }

                for (int i = 0; i < movie.getProductionCompany().size(); i++){
                    if(!productionCompanyList.contains(movie.getProductionCompany().get(i))){
                        productionCompanyList.add(movie.getProductionCompany().get(i));
                        productionCompanyList.sort(String::compareToIgnoreCase);
                    }
                }

                for (int i = 0; i < movie.getCountry().size(); i++){
                    if(!countryList.contains(movie.getCountry().get(i))){
                        countryList.add(movie.getCountry().get(i));
                        countryList.sort(String::compareToIgnoreCase);
                    }
                }

                if(!languageList.contains(movie.getLanguage())){
                    languageList.add(movie.getLanguage());
                    languageList.sort(String::compareToIgnoreCase);
                }
            }

            // Putting data on the web page
            fields.put("title", titleList);
            fields.put("genre", genreList);
            fields.put("yearOfRelease", yearOfReleaseList);
            fields.put("starring", starringList);
            fields.put("directedBy", directedByList);
            fields.put("producedBy", producedByList);
            fields.put("writtenBy", writtenByList);
            fields.put("productionCompany", productionCompanyList);
            fields.put("country", countryList);
            fields.put("language", languageList);
            return new ModelAndView(fields,"index.hbs");
        }, new HandlebarsTemplateEngine());

        // Filters
        post("/api/filterTitle", (request, response) -> {
            response.type("text/text");

            String filteredTitle = request.queryParams("title");
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                if(movie.getTitle().equals(filteredTitle)){
                    list.add(movie);
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });
        post("/api/filterGenre", (request, response) -> {
            response.type("text/text");

            String filteredGenre = request.queryParams("genre");
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                if(movie.getGenre().equals(filteredGenre)){
                    list.add(movie);
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });
        post("/api/filterYearOfRelease", (request, response) -> {
            response.type("text/text");

            int filteredYearOfRelease = Integer.parseInt(request.queryParams("yearOfRelease"));
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                if(movie.getYearOfRelease() == filteredYearOfRelease){
                    list.add(movie);
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });
        post("/api/filterStarring", (request, response) -> {
            response.type("text/text");

            String filteredStarring = request.queryParams("starring");
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                for (int i = 0; i < movie.getStarring().size(); i++){
                    if(movie.getStarring().get(i).equals(filteredStarring)){
                        list.add(movie);
                    }
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });
        post("/api/filterProducedBy", (request, response) -> {
            response.type("text/text");

            String filteredProducedBy = request.queryParams("producedBy");
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                for (int i = 0; i < movie.getProducedBy().size(); i++) {
                    if (movie.getProducedBy().get(i).equals(filteredProducedBy)) {
                        list.add(movie);
                    }
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });
        post("/api/filterDirectedBy", (request, response) -> {
            response.type("text/text");

            String filteredDirectedBy = request.queryParams("directedBy");
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                for (int i = 0; i < movie.getDirectedBy().size(); i++) {
                    if (movie.getDirectedBy().get(i).equals(filteredDirectedBy)) {
                        list.add(movie);
                    }
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });
        post("/api/filterWrittenBy", (request, response) -> {
            response.type("text/text");

            String filteredWrittenBy = request.queryParams("writtenBy");
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                for (int i = 0; i < movie.getWrittenBy().size(); i++) {
                    if (movie.getWrittenBy().get(i).equals(filteredWrittenBy)) {
                        list.add(movie);
                    }
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });
        post("/api/filterProductionCompany", (request, response) -> {
            response.type("text/text");

            String filteredProductionCompany = request.queryParams("productionCompany");
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                for (int i = 0; i < movie.getProductionCompany().size(); i++) {
                    if (movie.getProductionCompany().get(i).equals(filteredProductionCompany)) {
                        list.add(movie);
                    }
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });
        post("/api/filterCountry", (request, response) -> {
            response.type("text/text");

            String filteredCountry = request.queryParams("country");
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                for (int i = 0; i < movie.getCountry().size(); i++) {
                    if (movie.getCountry().get(i).equals(filteredCountry)) {
                        list.add(movie);
                    }
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });
        post("/api/filterLanguage", (request, response) -> {
            response.type("text/text");

            String filteredLanguage = request.queryParams("language");
            ArrayList<Movie> list = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                if(movie.getLanguage().equals(filteredLanguage)){
                    list.add(movie);
                }
            }
            Gson gson = new Gson();
            return gson.toJson(list);
        });

        /* ********* *
         * Add movie *
         * ********* */
        get("/addMovie",(request, response) -> {
            return new ModelAndView(fields,"addMovie.hbs");
        }, new HandlebarsTemplateEngine());

        post("/api/addNewMovie",(request, response) -> {
            response.type("application/json");

            String inputTitle = request.queryParams("inputTitle");
            String inputGenre = request.queryParams("inputGenre");
            int inputRunningTime = Integer.parseInt(request.queryParams("inputRunningTime").trim());
            int inputYearOfRelease = Integer.parseInt(request.queryParams("inputYearOfRelease").trim());
            String inputLanguage = request.queryParams("inputLanguage");
            String inputBudget = request.queryParams("inputBudget");
            ArrayList<String> starringArray = makeList(request.queryParams("starringArray"));
            ArrayList<String> directedByArray = makeList(request.queryParams("directedByArray"));
            ArrayList<String> producedByArray = makeList(request.queryParams("producedByArray"));
            ArrayList<String> writtenByArray = makeList(request.queryParams("writtenByArray"));
            ArrayList<String> productionCompanyArray = makeList(request.queryParams("productionCompanyArray"));
            ArrayList<String> countryArray = makeList(request.queryParams("countryArray"));

            ArrayList<Movie> movies = Data.readFromJson(path);

            movies.add(new Movie(inputTitle, inputGenre, inputRunningTime, starringArray, directedByArray, producedByArray, writtenByArray, productionCompanyArray, countryArray, inputLanguage, inputYearOfRelease, inputBudget));
            Data.writeToJSON(movies, path);
            Gson gson = new Gson();
            return gson.toJson("Changes saved");
        });

        /* ************ *
         * Modify movie *
         * ************ */
        get("/modifyMovie",(request, response) -> {
            // adding the title filter
            fields.put("moviesData", Data.readFromJson(path));
            
            ArrayList<String> titleList = new ArrayList<>();
            for (Movie movie : Data.readFromJson(path)){
                if(!titleList.contains(movie.getTitle())){
                    titleList.add(movie.getTitle());
                    titleList.sort(String::compareToIgnoreCase);
                }
            }
            fields.put("title", titleList);
            return new ModelAndView(fields,"modifyMovie.hbs");
        }, new HandlebarsTemplateEngine());

        post("/api/modifyMovie",(request, response) -> {
            response.type("application/json");

            String title = request.queryParams("title");
            String inputTitle = request.queryParams("inputTitle");
            String inputGenre = request.queryParams("inputGenre");
            int inputRunningTime = Integer.parseInt(request.queryParams("inputRunningTime").trim());
            int inputYearOfRelease = Integer.parseInt(request.queryParams("inputYearOfRelease").trim());
            String inputLanguage = request.queryParams("inputLanguage");
            String inputBudget = request.queryParams("inputBudget");
            ArrayList<String> starringArray = makeList(request.queryParams("starringArray"));
            ArrayList<String> directedByArray = makeList(request.queryParams("directedByArray"));
            ArrayList<String> producedByArray = makeList(request.queryParams("producedByArray"));
            ArrayList<String> writtenByArray = makeList(request.queryParams("writtenByArray"));
            ArrayList<String> productionCompanyArray = makeList(request.queryParams("productionCompanyArray"));
            ArrayList<String> countryArray = makeList(request.queryParams("countryArray"));

            ArrayList<Movie> movies = Data.readFromJson(path);

            for(Movie m : movies){
                if(!title.equals(null) && title.equals(m.getTitle())) {
                    if (!inputTitle.isEmpty() && inputTitle != null) {
                        m.setTitle(inputTitle);
                    }
                    if (!inputGenre.isEmpty() && inputGenre != null) {
                        m.setGenre(inputGenre);
                    }
                    if (inputRunningTime != 0) {
                        m.setRunningTime(inputRunningTime);
                    }
                    if (inputYearOfRelease != 0) {
                        m.setYearOfRelease(inputYearOfRelease);
                    }
                    if (!inputLanguage.isEmpty() && inputLanguage != null) {
                        m.setLanguage(inputLanguage);
                    }
                    if (!inputBudget.isEmpty() && inputBudget != null) {
                        m.setBudget(inputBudget);
                    }
                    if (starringArray.size() != 1 || !starringArray.get(0).equals("")) {
                        m.setStarring(starringArray);
                    }
                    if (directedByArray.size() != 1 || !directedByArray.get(0).equals("")) {
                        m.setDirectedBy(directedByArray);
                    }
                    if (producedByArray.size() != 1 || !producedByArray.get(0).equals("")) {
                        m.setProducedBy(producedByArray);
                    }
                    if (writtenByArray.size() != 1 || !writtenByArray.get(0).equals("")) {
                        m.setWrittenBy(writtenByArray);
                    }
                    if (productionCompanyArray.size() != 1 || !productionCompanyArray.get(0).equals("")) {
                        m.setProductionCompany(productionCompanyArray);
                    }
                    if (countryArray.size() != 1 || !countryArray.get(0).equals("")) {
                        m.setCountry(countryArray);
                    }
                }
            }
            Data.writeToJSON(movies, path);
            Gson gson=new Gson();
            return gson.toJson("Changes saved");
        });

        post("/api/removeMovie",(request, response) -> {
            response.type("application/json");
            String title = request.queryParams("title");
            ArrayList<Movie> movies = Data.readFromJson(path);

            for (int i = 0; i < movies.size(); i++) {
                if (title.equals(movies.get(i).getTitle())) {
                    movies.remove(i);
                    break;
                }
            }
            Data.writeToJSON(movies, path);
            Gson gson=new Gson();
            return gson.toJson("Changes saved");
        });
    }


    public static ArrayList<String> makeList(String str) {
        ArrayList<String> words = new ArrayList<>();
        String withoutBrackets = str.replaceAll("[\\[\\](){}]", ""); // Remove all the brackets
        for (String word : withoutBrackets.split(",")) {
            String singleWord = word.replaceAll("\"", "");
            words.add(singleWord);
        }
        return words;
    }
}
