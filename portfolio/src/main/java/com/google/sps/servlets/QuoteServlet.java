// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/quote")
public class QuoteServlet extends HttpServlet {
    
    private List<String> quotes;

    @Override
    public void init() {
        quotes = new ArrayList<>();
        quotes.add(
            "A little slope makes up for alot of y-intercept");
        quotes.add(
            "A good hack for surrounding yourself by influential people, if you can't do that, is reading about them.");
        quotes.add(
            "You are where you are. What you do next is more important."
        );
    }

    @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String quote = quotes.get((int) (Math.random() * quotes.size()));
        response.setContentType("text/html;");
        response.getWriter().println(quote);    
    }
}
