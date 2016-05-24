package org.designwizard.extractor.asm.visitor;

import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.signature.SignatureVisitor;

public class GenericTypeSignatureVisitor implements SignatureVisitor {
	
	private Set<String> types;

	public GenericTypeSignatureVisitor() {
		this.types = new HashSet<>();
	}
	
	public Set<String> getTypes() {
		return types;
	}
	
	@Override
	public void visitFormalTypeParameter(String name) {
		System.out.println("MethodSignatureVisitor.visitFormalTypeParameter(name): " + name);
	}

	@Override
	public SignatureVisitor visitClassBound() {
		System.out.println("MethodSignatureVisitor.visitClassBound()");
		return this;
	}

	@Override
	public SignatureVisitor visitInterfaceBound() {
		System.out.println("MethodSignatureVisitor.visitInterfaceBound()");
		return this;
	}

	@Override
	public SignatureVisitor visitSuperclass() {
		System.out.println("MethodSignatureVisitor.visitSuperclass()");
		return this;
	}

	@Override
	public SignatureVisitor visitInterface() {
		System.out.println("MethodSignatureVisitor.visitInterface()");
		return this;
	}

	@Override
	public SignatureVisitor visitParameterType() {
		System.out.println("MethodSignatureVisitor.visitParameterType()");
		return this;
	}

	@Override
	public SignatureVisitor visitReturnType() {
		System.out.println("MethodSignatureVisitor.visitReturnType()");
		return this;
	}

	@Override
	public SignatureVisitor visitExceptionType() {
		System.out.println("MethodSignatureVisitor.visitExceptionType()");
		return this;
	}

	@Override
	public void visitBaseType(char descriptor) {
		System.out.println("MethodSignatureVisitor.visitInnerClassType(descriptor): " + descriptor);
		//if (descriptor != 'V') {
			//System.out.println("visitBaseType" + descriptor);
		//}
	}

	@Override
	public void visitTypeVariable(String name) {
		System.out.println("MethodSignatureVisitor.visitTypeVariable(name): " + name);
		types.add(name);
	}

	@Override
	public SignatureVisitor visitArrayType() {
		System.out.println("MethodSignatureVisitor.visitArrayType()");
		return this;
	}

	@Override
	public void visitClassType(String name) {
		System.out.println("MethodSignatureVisitor.visitClassType(name): " + name);
		types.add(name);
	}

	@Override
	public void visitInnerClassType(String name) {
		System.out.println("MethodSignatureVisitor.visitInnerClassType(name): " + name);
		visitClassType(name);
	}

	@Override
	public void visitTypeArgument() {
		System.out.println("MethodSignatureVisitor.visitTypeArgument()");
	}

	@Override
	public SignatureVisitor visitTypeArgument(char wildcard) {
		System.out.println("MethodSignatureVisitor.visitTypeArgument(wildcard): " + wildcard);
		return this;
	}

	@Override
	public void visitEnd() {
		System.out.println("MethodSignatureVisitor.visitEnd()");
	}
}
