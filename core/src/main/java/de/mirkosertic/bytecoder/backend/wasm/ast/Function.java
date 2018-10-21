/*
 * Copyright 2018 Mirko Sertic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mirkosertic.bytecoder.backend.wasm.ast;

import java.io.IOException;
import java.util.List;

public class Function implements Importable {

    private final FunctionType functionType;
    private final String label;
    private final List<Param> params;
    private final PrimitiveType resultType;

    Function(final FunctionType functionType, final String label, final List<Param> params, final PrimitiveType result) {
        this.functionType = functionType;
        this.label = label;
        this.params = params;
        this.resultType = result;
    }

    Function(final FunctionType functionType, final String label, final List<Param> params) {
        this.functionType = functionType;
        this.label = label;
        this.params = params;
        this.resultType = null;
    }

    Function(final FunctionType functionType, final String label, final PrimitiveType result) {
        this.functionType = functionType;
        this.label = label;
        this.params = null;
        this.resultType = result;
    }

    @Override
    public void writeTo(final TextWriter textWriter) throws IOException {
        textWriter.opening();
        textWriter.write("func");
        textWriter.space();
        textWriter.writeLabel(label);
        if (null != params) {
            for (final Param param : params) {
                textWriter.space();
                param.writeTo(textWriter);
            }
        }
        if (null != resultType) {
            textWriter.space();
            textWriter.opening();
            textWriter.write("result");
            textWriter.space();
            resultType.writeTo(textWriter);
            textWriter.closing();
        }
        textWriter.closing();
    }

    public FunctionType getFunctionType() {
        return functionType;
    }

    public String getLabel() {
        return label;
    }

    public List<Param> getParams() {
        return params;
    }

    public PrimitiveType getResultType() {
        return resultType;
    }
}