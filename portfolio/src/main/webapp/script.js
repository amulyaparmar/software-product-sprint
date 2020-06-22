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

/**
 * Adds a random greeting to the page.
 */
function addRandomQuoteManually() {
  const greetings =
      ['A little slope makes up for a lot of y-intercept', 'By Failing to prepare, you are preparing to fail.'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

// Get data from QuoteServlet - Random Quote Generator
async function getRandomQuote() {
    const response = await fetch('/quote');
    const quote = await response.text();
    document.getElementById('quote-container').innerHTML = "<b>Favorite Quote </b>: " + quote;
}

// Get data from the DataServlet
async function getData() {
    const response = await fetch('/data');
    const data = await response.json();
    
    var str = '<h2>Comments:</h2><ul>'

    data["comments"].forEach(function(comment) {
    str += '<li>'+ comment + '</li>';
    }); 

    str += '</ul>';
    document.getElementById("data-container").innerHTML = str;

    console.log(data);
}
