 line = bufferedReader.readLine().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
                                
                                if(regexCounter == blocoDeRegexOrganizado.size()){
                                    regexCounter = 0;
                                    matcherHighlightRegex = null;
                                    highlightLineMatched = null;
                                }                               
                                while (isMatchedRegex) {
                                    String regexValue = blocoDeRegexOrganizado.get(regexCounter).getValue().toString();
                                    
                                    try {
                                        pattern = Pattern.compile(regexValue);
                                        Matcher matcher = pattern.matcher(line);
                                        isMatchedRegex = matcher.find();                                                                                
                                        if(isMatchedRegex){
                                            if(regexCounter==highlightRegexPosition){
                                                matcherHighlightRegex = matcher;
                                                highlightLineMatched = line;
                                                highlightLineNumber = lineNumber;
                                            }
                                            regexCounter++;
                                            break;
                                        }
                                    } catch (PatternSyntaxException pse) {
                                        isMatchedRegex = false;
                                    }
                                }
                                if(isMatchedRegex && regexCounter == blocoDeRegexOrganizado.size()){
                                     geradorRelatorio.prepareDataToFonteDados(file, caminhoAtual, entryLabel.getKey().toString(), entryLabel.getValue().toString(), 
                                        highlightLineMatched, highlightLineNumber, matcherHighlightRegex);
                                }
                                if(!isMatchedRegex){                                    
                                    isMatchedRegex = true;
                                    regexCounter = 0;
                                }