## Integrating LLMs into Existing Web Test Automation

Web test automation is often fragile, time-consuming, and expensive to maintain. 
This demo explores how Large Language Models (LLMs) can generate XPaths dynamically, 
improving script creation efficiency and enhancing self-healing capabilities.  

**Tooling**  
* **Java-21** 
* **langchain4j-0.32.0**
* **Google Gemini-1.5-flash** LLM
* **Selenium-4.26**(Demo use case)

**Application Considerations**  

**HTML source size:**   
If a page source is overly large or complex, LLM may hallucinate. Consider using web page component(header, left rail etc) 
to reduce HTML source size and improve accuracy and performance.


**Costs Optimization**   

To minimize token usage and costs:

* **Cache XPaths** (only relative XPaths are cache-friendly).

* **Batch requests**—generate all XPaths for a given page in a single LLM call.


**Usability Improvements**  

Add facade API to improve easy-to-use  

//Input value to form field, checkbox etc  
*public void assignElementWithValue(String elementDescription, String value)* 

//Click a link, button etc   
*public void clickElment(String elementDescription)*
