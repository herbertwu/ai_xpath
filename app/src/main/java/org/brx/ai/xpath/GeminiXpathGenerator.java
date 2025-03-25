package org.brx.ai.xpath;

import org.apache.commons.lang3.StringUtils;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class GeminiXpathGenerator implements LLMXpathGenerator {
	private final String NOT_FOUND_RESULT="not_found";
	 
	 private GoogleAiGeminiChatModel model;

	public GeminiXpathGenerator(String GEMINI_AI_KEY, String modelName) {
		model = GoogleAiGeminiChatModel.builder()
			    .apiKey(GEMINI_AI_KEY)
			    .modelName(modelName)
			    .build();
	}


	@Override
	public String generateRelativeXpath(String htmlContext, String elementDescription) {
		String prompt = createPrompt(htmlContext,elementDescription);
		String xpath = parseResponse(model.chat(prompt));
		if (NOT_FOUND_RESULT.equalsIgnoreCase(xpath)) {
			throw new RuntimeException("The described element can not be found.");
		} else {
			return xpath;
		}
	}


	private String parseResponse(String chat) {
		return StringUtils.chomp(chat).replaceAll("\s+", " ");
	}


	private String createPrompt(String htmlSource, String elementDescription) {
		return String.format("Act as a software QA engineer, for the following html code, please generate a relative xpath(without any additional description) for the following web element: %s. Say 'not_found' if the web element does not exist.\n\n%s",elementDescription, htmlSource);
	}

}
