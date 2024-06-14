package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results")
    public String displaySearchResults(Model model,
                                       @RequestParam String searchType,
                                       @RequestParam String searchTerm) {
        ArrayList<Job> jobs;

//        if (searchTerm.toLowerCase().equals("all") || searchTerm.isEmpty()) {
//            jobs = JobData.findAll();
//            model.addAttribute("title", "All Jobs");
//        } else {
//            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//            String columnLabel = columnChoices.get(searchType);
//            model.addAttribute("title", "Jobs with " + columnLabel + ": " + searchTerm);
//        }

        // Check if the search term is "all" or empty
        if (searchTerm.toLowerCase().equals("all") || searchTerm.trim().isEmpty()) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        // Pass the search results and column choices to the view
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("title", "Jobs with " + searchType + ": " + searchTerm);
        model.addAttribute("searchTerm", searchTerm);

        return "search";
    }
}

