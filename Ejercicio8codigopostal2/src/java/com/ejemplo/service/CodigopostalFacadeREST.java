package com.ejemplo.service;

import com.ejemplo.Codigopostal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("codigopostal")
public class CodigopostalFacadeREST extends AbstractFacade<Codigopostal> {

    @PersistenceContext(unitName = "CodigoPostalPU")
    private EntityManager em;

    public CodigopostalFacadeREST() {
        super(Codigopostal.class);
    }
    
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Codigopostal entity) {
        super.create(entity);
    }

    @PUT
    @Path("/id/{id : \\d+}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Codigopostal entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("/id/{id : \\d+}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/id/{id : \\d+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Codigopostal find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("/id")
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Codigopostal> findAll() {
        return super.findAll();
    }

    @GET
    @Path("/id/{from : \\d+}/{to : \\d+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Codigopostal> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Produces("text/html")
    public Response allCP(){
        return mostrarDatos(super.findAll());
    }
    
    @GET
    @Path("{codigo : \\d+}")
    @Produces("text/html")
    public Response findByCodigo(@PathParam("codigo") String codigo){
        return mostrarDatos(super.findByCodigo(codigo));
        
    }
    
    private Response mostrarDatos(List<Codigopostal> datos){
        StringBuilder sb = new StringBuilder();
        
        sb.append("<body>\n");
        if(datos.size() == 0){
            sb.append("No se encontraron resultados");
        }else{
            sb.append("  <table>\n");
            sb.append("    <tr>\n");
            sb.append("      <th>Codigo</th>\n");
            sb.append("      <th>Colonia</th>\n");
            sb.append("    </tr>\n");
            for(Codigopostal cp : datos){
                sb.append("    <tr>\n");
                sb.append("      <td>").append(cp.getCodigo()).append("</td>\n");
                sb.append("      <td>").append(cp.getColonia()).append("</td>\n");
                sb.append("    </tr>\n");
            }
            sb.append("  </table>\n");
        }
        sb.append("</body>");
        return Response.status(200).entity(sb.toString()).build();
    }
}
