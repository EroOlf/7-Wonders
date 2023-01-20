package com.example.the7wonders.domain.tokens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProgressTokens extends Tokens{

	private static List<ProgressToken> progressTokens;
	
	// there is 1 token of each type, and 2 tokens of the same 'Culture' type

	public ProgressTokens(){
		progressTokens = createTokenList();
	}

	private static List<ProgressToken> createTokenList() {
		List<ProgressToken> res = new ArrayList<>();
		res.addAll(Arrays.asList(ProgressToken.values()));
		res.add(ProgressToken.Culture);
		return res;
	}

	public List<ProgressToken> getProgressTokens() {
		return progressTokens;
	}
}
