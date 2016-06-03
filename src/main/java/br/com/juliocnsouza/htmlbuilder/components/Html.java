package br.com.juliocnsouza.htmlbuilder.components;

import br.com.juliocnsouza.htmlbuilder.AbstractBuilder;
import br.com.juliocnsouza.htmlbuilder.HtmlComponent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public class Html extends AbstractBuilder {

    private final Head head;
    private final List<HtmlComponent> components;
    private final List<String> scripts;
    private final List<String> links;

    public Html() {
        head = new Head();
        components = new ArrayList<>();
        scripts = new ArrayList<>();
        links = new ArrayList<>();
    }

    public Head getHead() {
        return head;
    }

    public void addComponent( HtmlComponent component ) {
        if ( component == null ) {
            return;
        }
        components.add( component );
    }

    public void addScript( String script ) {
        if ( script == null ) {
            return;
        }
        scripts.add( "<script>" + script + "</script>" );
    }

    public void addLinkJS( String src ) {
        if ( src == null ) {
            return;
        }
        links.add( "<script type=\"text/javascript\" src=\"" + src + "\"></script>" );
    }

    @Override
    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append( "<!DOCTYPE html><html>" );
        builder.append( head.build() );
        builder.append( "<body>" );
        builder.append( buildComponents() );
        builder.append( buildScripts() );
        builder.append( buildJSLInks() );
        builder.append( "</body>" );
        builder.append( "</html>" );
        return builder.toString();
    }

    private String buildComponents() {
        StringBuilder componentsBuilder = new StringBuilder( "" );
        if ( !components.isEmpty() ) {
            components.stream()
                    .filter( component -> component != null )
                    .forEach( ( component ) -> {
                        componentsBuilder.append( component.build() );
                    } );
        }
        return componentsBuilder.toString();
    }

    private String buildScripts() {
        StringBuilder scriptBuildersBuilder = new StringBuilder( "" );
        if ( !scripts.isEmpty() ) {
            scripts.stream()
                    .filter( script -> script != null )
                    .forEach( ( script ) -> {
                        scriptBuildersBuilder.append( script );
                    } );
        }
        return scriptBuildersBuilder.toString();
    }

    private String buildJSLInks() {
        StringBuilder scriptBuildersBuilder = new StringBuilder( "" );
        if ( !links.isEmpty() ) {
            links.stream()
                    .filter( script -> script != null )
                    .forEach( ( script ) -> {
                        scriptBuildersBuilder.append( script );
                    } );
        }
        return scriptBuildersBuilder.toString();
    }

}