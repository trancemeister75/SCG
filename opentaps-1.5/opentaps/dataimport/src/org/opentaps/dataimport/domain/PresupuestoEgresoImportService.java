package org.opentaps.dataimport.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilDateTime;
import org.opentaps.base.constants.StatusItemConstants;
import org.opentaps.base.entities.AcctgTrans;
import org.opentaps.base.entities.AcctgTransEntry;
import org.opentaps.base.entities.AcctgTransPresupuestal;
import org.opentaps.base.entities.ClasifPresupuestal;
import org.opentaps.base.entities.CustomTimePeriod;
import org.opentaps.base.entities.DataImportPresupuestoEgreso;
import org.opentaps.base.entities.Enumeration;
import org.opentaps.base.entities.EstructuraClave;
import org.opentaps.base.entities.Geo;
import org.opentaps.base.entities.GlAccountHistory;
import org.opentaps.base.entities.GlAccountOrganization;
import org.opentaps.base.entities.LoteTransaccion;
import org.opentaps.base.entities.MiniGuiaContable;
import org.opentaps.base.entities.Party;
import org.opentaps.base.entities.ProductCategory;
import org.opentaps.base.entities.WorkEffort;
import org.opentaps.dataimport.UtilImport;
import org.opentaps.domain.DomainService;
import org.opentaps.domain.dataimport.PresupuestoEgresoDataImportRepositoryInterface;
import org.opentaps.domain.dataimport.PresupuestoEgresoImportServiceInterface;
import org.opentaps.domain.ledger.LedgerRepositoryInterface;
import org.opentaps.foundation.entity.hibernate.Session;
import org.opentaps.foundation.entity.hibernate.Transaction;
import org.opentaps.foundation.infrastructure.Infrastructure;
import org.opentaps.foundation.infrastructure.InfrastructureException;
import org.opentaps.foundation.infrastructure.User;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.foundation.service.ServiceException;

public class PresupuestoEgresoImportService extends DomainService implements
		PresupuestoEgresoImportServiceInterface {
	private static final String MODULE = PresupuestoEgresoImportService.class
			.getName();
	// session object, using to store/search pojos.
	private Session session;
	private String organizationPartyId;
	public int importedRecords;
	private String lote;

	public PresupuestoEgresoImportService() {
		super();
	}

	public PresupuestoEgresoImportService(Infrastructure infrastructure,
			User user, Locale locale) throws ServiceException {
		super(infrastructure, user, locale);
	}

	/** {@inheritDoc} */
	public void setOrganizationPartyId(String organizationPartyId) {
		this.organizationPartyId = organizationPartyId;
	}

	/** {@inheritDoc} */
	public void setLote(String lote) {
		this.lote = lote;
	}

	/** {@inheritDoc} */
	public int getImportedRecords() {
		return importedRecords;
	}

	/** {@inheritDoc} */
	public void importPresupuestoEgreso() throws ServiceException {
		try {
			this.session = this.getInfrastructure().getSession();

			PresupuestoEgresoDataImportRepositoryInterface imp_repo = this
					.getDomainsDirectory().getDataImportDomain()
					.getPresupuestoEgresoDataImportRepository();

			LedgerRepositoryInterface ledger_repo = this.getDomainsDirectory()
					.getLedgerDomain().getLedgerRepository();

			List<DataImportPresupuestoEgreso> dataforimp = imp_repo
					.findNotProcessesDataImportPresupuestoEgresoEntries();

			int imported = 0;
			Transaction imp_tx1 = null;
			Transaction imp_tx2 = null;
			Transaction imp_tx3 = null;
			Transaction imp_tx4 = null;

			if (UtilImport.validaLote(ledger_repo, lote, "PresupuestoEgreso")) {
				boolean loteValido = true;
				for (DataImportPresupuestoEgreso rowdata : dataforimp) {
					// Validaciones
					ContenedorContable contenedor = new ContenedorContable();
					String mensaje = "";
					Debug.log("Empieza bloque de validaciones");
					// mensaje = UtilImport.validaParty(mensaje, ledger_repo,
					// rowdata.getUr(), "UR");
					// mensaje = UtilImport.validaParty(mensaje, ledger_repo,
					// rowdata.getUo(), "UO");
					// mensaje = UtilImport.validaParty(mensaje, ledger_repo,
					// rowdata.getUe(), "UE");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getFin(), "CLAS_FUN", "FIN");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getFun(), "CLAS_FUN", "FUN");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getSubf(), "CLAS_FUN", "SUBF");
					// mensaje = UtilImport.validaWorkEffort(mensaje,
					// ledger_repo,
					// rowdata.getEje(), "EJE");
					// mensaje = UtilImport.validaWorkEffort(mensaje,
					// ledger_repo,
					// rowdata.getPp(), "PP");
					// mensaje = UtilImport.validaWorkEffort(mensaje,
					// ledger_repo,
					// rowdata.getSpp(), "SPP");
					// mensaje = UtilImport.validaWorkEffort(mensaje,
					// ledger_repo,
					// rowdata.getAct(), "ACT");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getTg(), "TIPO_GASTO", "tg");
					// mensaje = UtilImport.validaProductCategory(mensaje,
					// ledger_repo, rowdata.getCap(), "CA", "CAP");
					// mensaje = UtilImport.validaProductCategory(mensaje,
					// ledger_repo, rowdata.getCon(), "CON", "CON");
					// mensaje = UtilImport.validaProductCategory(mensaje,
					// ledger_repo, rowdata.getPg(), "PG", "PG");
					// mensaje = UtilImport.validaProductCategory(mensaje,
					// ledger_repo, rowdata.getPe(), "PE", "PE");
					// mensaje = UtilImport.validaGeo(mensaje, ledger_repo,
					// rowdata.getEf(), "EF");
					// mensaje = UtilImport.validaGeo(mensaje, ledger_repo,
					// rowdata.getReg(), "REG");
					// mensaje = UtilImport.validaGeo(mensaje, ledger_repo,
					// rowdata.getMun(), "MUN");
					// mensaje = UtilImport.validaGeo(mensaje, ledger_repo,
					// rowdata.getLoc(), "LOC");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getF(), "CLAS_FR", "F");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getSf(), "CLAS_FR", "SF");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getSfe(), "CLAS_FR", "SFE");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getSec(), "CLAS_SECT", "SEC");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getSubsec(), "CLAS_SECT", "SUBSEC");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo,
					// rowdata.getArea(), "CLAS_SECT", "AREA");

					// clasificaciones
					// mensaje = UtilImport.validaParty(mensaje, ledger_repo,
					// rowdata.getUe(), "ADMINISTRATIVA");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo, rowdata.getSubf(), "CL_FUNCIONAL",
					// "FUNCIONAL");
					// mensaje = UtilImport.validaWorkEffort(mensaje,
					// ledger_repo,
					// rowdata.getAct(), "ACTIVIDAD");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo, rowdata.getTg(), "TIPO_GASTO",
					// "TIPO GASTO");
					// mensaje = UtilImport.validaProductCategory(mensaje,
					// ledger_repo, rowdata.getPe(), "PARTIDA ESPECIFICA",
					// "PRODUCTO ESPECIFICO");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo, rowdata.getSfe(),
					// "CL_FUENTE_RECURSOS", "FUENTE DE LOS RECURSOS");
					// mensaje = UtilImport.validaGeo(mensaje, ledger_repo,
					// rowdata.getLoc(), "GEOGRAFICA");
					// mensaje = UtilImport.validaEnumeration(mensaje,
					// ledger_repo, rowdata.getArea(), "CL_SECTORIAL",
					// "SECTORIAL");
					String anio = "20" + rowdata.getCiclo();

					// Se obtiene la estructura de la clave valida para el ciclo
					EstructuraClave estructura = ledger_repo.findList(
							EstructuraClave.class,
							ledger_repo.map(EstructuraClave.Fields.ciclo, anio,
									EstructuraClave.Fields.acctgTagUsageTypeId,
									"EGRESO")).get(0);
					// Se obtiene el tipo de clasficacion
					List<Clasificacion> listaClasif = new ArrayList<Clasificacion>();
					if (estructura.getClasificacion1() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif1 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion1()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif1);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion1()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion2() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif2 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion2()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif2);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion2()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion3() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif3 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion3()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif3);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion3()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion4() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif4 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion4()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif4);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion4()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion5() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif5 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion5()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif5);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion5()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion6() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif6 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion6()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif6);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion6()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion7() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif7 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion7()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif7);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion7()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion8() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif8 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion8()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif8);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion8()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion9() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif9 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion9()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif9);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion9()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion10() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif10 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion10()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif10);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion10()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion11() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif11 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion11()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif11);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion11()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion12() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif12 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion12()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif12);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion12()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion13() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif13 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion13()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif13);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion13()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion14() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif14 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion14()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif14);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion14()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}
					if (estructura.getClasificacion15() != null) {
						Clasificacion c = new Clasificacion();
						String tipoClasif15 = ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion15()))
								.get(0).getTablaRelacion();
						c.setTipoObjeto(tipoClasif15);
						c.setValor(rowdata.getClasificacion1());
						c.setTipoEnum(ledger_repo
								.findList(
										ClasifPresupuestal.class,
										ledger_repo
												.map(ClasifPresupuestal.Fields.clasificacionId,
														estructura
																.getClasificacion15()))
								.get(0).getClasificacionId());
						listaClasif.add(c);
					}

					// Creacion de objetos
					Debug.log("Empieza creacion de objetos");
					// Party ur = UtilImport.obtenParty(ledger_repo,
					// rowdata.getUr());
					// Party uo = UtilImport.obtenParty(ledger_repo,
					// rowdata.getUo());
					// Party ue = UtilImport.obtenParty(ledger_repo,
					// rowdata.getUe());
					// Enumeration fin =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getFin(), "CLAS_FUN");
					// Enumeration fun =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getFun(), "CLAS_FUN");
					// Enumeration subf =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getSubf(), "CLAS_FUN");
					// WorkEffort eje = UtilImport.obtenWorkEffort(ledger_repo,
					// rowdata.getEje());
					// WorkEffort pp = UtilImport.obtenWorkEffort(ledger_repo,
					// rowdata.getPp());
					// WorkEffort spp = UtilImport.obtenWorkEffort(ledger_repo,
					// rowdata.getSpp());
					// WorkEffort act = UtilImport.obtenWorkEffort(ledger_repo,
					// rowdata.getAct());
					// Enumeration tg = UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getFin(), "TIPO_GASTO");
					// ProductCategory cap = UtilImport.obtenProductCategory(
					// ledger_repo, rowdata.getCap(), "CA");
					// ProductCategory con = UtilImport.obtenProductCategory(
					// ledger_repo, rowdata.getCon(), "CON");
					// ProductCategory pg = UtilImport.obtenProductCategory(
					// ledger_repo, rowdata.getPg(), "PG");
					// ProductCategory pe = UtilImport.obtenProductCategory(
					// ledger_repo, rowdata.getPe(), "PE");
					// Geo ef = UtilImport.obtenGeo(ledger_repo,
					// rowdata.getEf());
					// Geo reg = UtilImport.obtenGeo(ledger_repo,
					// rowdata.getReg());
					// Geo mun = UtilImport.obtenGeo(ledger_repo,
					// rowdata.getMun());
					// Geo loc = UtilImport.obtenGeo(ledger_repo,
					// rowdata.getLoc());
					// Enumeration f = UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getF(), "CLAS_FR");
					// Enumeration sf = UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getSf(), "CLAS_FR");
					// Enumeration sfe =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getSfe(), "CLAS_FR");
					// Enumeration sec =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getSec(), "CLAS_SECT");
					// Enumeration subsec =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getSubsec(), "CLAS_SECT");
					// Enumeration area =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getArea(), "CLAS_SECT");

					// clasificaciones
					// Party ue = UtilImport.obtenParty(ledger_repo,
					// rowdata.getUe());
					// Enumeration subf =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getSubf(), "CL_FUNCIONAL");
					// WorkEffort act = UtilImport.obtenWorkEffort(ledger_repo,
					// rowdata.getAct());
					// Enumeration tg = UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getTg(), "TIPO_GASTO");
					// ProductCategory pe = UtilImport.obtenProductCategory(
					// ledger_repo, rowdata.getPe(), "PARTIDA ESPECIFICA");
					// Geo loc = UtilImport
					// .obtenGeo(ledger_repo, rowdata.getLoc());
					// Enumeration sfe =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getSfe(), "CL_FUENTE_RECURSOS");
					// Enumeration area =
					// UtilImport.obtenEnumeration(ledger_repo,
					// rowdata.getArea(), "CL_SECTORIAL");

					// import Presupuestos Egreso as many as possible
					try {
						// id maximo
						Debug.log("Busqueda idMax");
						String id = ledger_repo.getNextSeqId("AcctgTrans");
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.DAY_OF_MONTH, 1);
						// String anio = "20" + rowdata.getCiclo();
						cal.set(Calendar.YEAR, Integer.parseInt(anio));

						for (int mes = 1; mes < 13; mes++) {
							imp_tx1 = null;
							imp_tx2 = null;
							imp_tx3 = null;
							imp_tx4 = null;
							AcctgTrans presupuestoEgreso = new AcctgTrans();
							// clasificaciones
							// presupuestoEgreso.setDescription(rowdata
							// .getClavePres() + "-" + mes);

							// id Transaccion
							List<AcctgTrans> trans = ledger_repo
									.findList(AcctgTrans.class, ledger_repo
											.map(AcctgTrans.Fields.description,
													presupuestoEgreso
															.getDescription()));

							if (trans.isEmpty()) {
								Debug.log("Trans Nueva");
								// clasificaciones
								if (mes < 10) {
									presupuestoEgreso.setAcctgTransId(id + " E"
											+ rowdata.getCiclo() + "-0" + mes);
								} else {
									presupuestoEgreso.setAcctgTransId(id + " E"
											+ rowdata.getCiclo() + "-" + mes);
								}

								presupuestoEgreso.setCreatedByUserLogin(rowdata
										.getUsuario());
							} else {
								String message = "La transaccion ya existe";
								storeImportPresupuestoEgresoError(rowdata,
										message, imp_repo);
								continue;
							}

							cal.set(Calendar.MONTH, mes - 1);

							// Bloque de Validacion de Clasificaciones
							contenedor = UtilImport.validaClasificaciones(
									listaClasif, ledger_repo, "E",
									cal.getTime());
							if (!contenedor.getMensaje().isEmpty()
									|| !mensaje.isEmpty()) {
								loteValido = false;

								storeImportPresupuestoEgresoError(rowdata,
										contenedor.getMensaje(), imp_repo);
								continue;
							}

							if (!mensaje.isEmpty()) {
								loteValido = false;
								String message = "Failed to import Presupuesto Egreso ["
										// clasificaciones
										// + rowdata.getClavePres()
										+ "], Error message : " + mensaje;
								storeImportPresupuestoEgresoError(rowdata,
										message, imp_repo);
								continue;
							}

							presupuestoEgreso.setTransactionDate(new Timestamp(
									cal.getTimeInMillis()));
							presupuestoEgreso.setIsPosted("Y");
							presupuestoEgreso.setPostedDate(new Timestamp(cal
									.getTimeInMillis()));
							presupuestoEgreso
									.setAcctgTransTypeId("TPRESUPAPROBADO");
							presupuestoEgreso
									.setLastModifiedByUserLogin(rowdata
											.getUsuario());
							// clasificaciones
							// presupuestoEgreso.setWorkEffortId(act
							// .getWorkEffortId());
							// presupuestoEgreso.setPartyId(ue.getPartyId());
							presupuestoEgreso = UtilImport
									.setPartyWorkEffortEnAcctTrans(
											presupuestoEgreso, contenedor);

							switch (mes) {
							case 1:
								presupuestoEgreso.setPostedAmount(rowdata
										.getEnero());
								break;
							case 2:
								presupuestoEgreso.setPostedAmount(rowdata
										.getFebrero());
								break;
							case 3:
								presupuestoEgreso.setPostedAmount(rowdata
										.getMarzo());
								break;
							case 4:
								presupuestoEgreso.setPostedAmount(rowdata
										.getAbril());
								break;
							case 5:
								presupuestoEgreso.setPostedAmount(rowdata
										.getMayo());
								break;
							case 6:
								presupuestoEgreso.setPostedAmount(rowdata
										.getJunio());
								break;
							case 7:
								presupuestoEgreso.setPostedAmount(rowdata
										.getJulio());
								break;
							case 8:
								presupuestoEgreso.setPostedAmount(rowdata
										.getAgosto());
								break;
							case 9:
								presupuestoEgreso.setPostedAmount(rowdata
										.getSeptiembre());
								break;
							case 10:
								presupuestoEgreso.setPostedAmount(rowdata
										.getOctubre());
								break;
							case 11:
								presupuestoEgreso.setPostedAmount(rowdata
										.getNoviembre());
								break;
							case 12:
								presupuestoEgreso.setPostedAmount(rowdata
										.getDiciembre());
								break;
							}

							mensaje = UtilImport.validaMonto(
									presupuestoEgreso.getPostedAmount(),
									mensaje);

							if (!mensaje.isEmpty()) {
								loteValido = false;

								storeImportPresupuestoEgresoError(rowdata,
										mensaje, imp_repo);
								continue;
							}

							Debug.log("Obtencion Dinamico FiscalType");
							MiniGuiaContable miniguia = ledger_repo
									.findOne(
											MiniGuiaContable.class,
											ledger_repo
													.map(MiniGuiaContable.Fields.acctgTransTypeId,
															presupuestoEgreso
																	.getAcctgTransTypeId()));

							if (miniguia == null) {
								loteValido = false;
								String message = "Tipo de transaccion no registrada en MiniGuia";
								storeImportPresupuestoEgresoError(rowdata,
										message, imp_repo);
								continue;
							}

							presupuestoEgreso.setGlFiscalTypeId(miniguia
									.getGlFiscalTypeIdPres());
							imp_tx1 = this.session.beginTransaction();
							ledger_repo.createOrUpdate(presupuestoEgreso);
							imp_tx1.commit();

							// Obtenemos los padres de cada nivel.
							// clasificaciones
							// String uo =
							// UtilImport.obtenPadreParty(ledger_repo,
							// ue.getPartyId());
							// String ur =
							// UtilImport.obtenPadreParty(ledger_repo,
							// uo);
							// String fun = UtilImport.obtenPadreEnumeration(
							// ledger_repo, subf.getEnumId());
							// String fin = UtilImport.obtenPadreEnumeration(
							// ledger_repo, fun);
							// String spp = UtilImport.obtenPadreWorkEffort(
							// ledger_repo, act.getWorkEffortId());
							// String pp = UtilImport.obtenPadreWorkEffort(
							// ledger_repo, spp);
							// String eje = UtilImport.obtenPadreWorkEffort(
							// ledger_repo, pp);
							// String pg = UtilImport.obtenPadreProductCategory(
							// ledger_repo, pe.getProductCategoryId());
							// String con =
							// UtilImport.obtenPadreProductCategory(
							// ledger_repo, pg);
							// String cap =
							// UtilImport.obtenPadreProductCategory(
							// ledger_repo, con);
							// String sf = UtilImport.obtenPadreEnumeration(
							// ledger_repo, sfe.getEnumId());
							// String f = UtilImport.obtenPadreEnumeration(
							// ledger_repo, sf);
							// String mun =
							// UtilImport.obtenPadreGeo(ledger_repo,
							// loc.getGeoId());
							// String reg =
							// UtilImport.obtenPadreGeo(ledger_repo,
							// mun);
							// String ef = UtilImport.obtenPadreGeo(ledger_repo,
							// reg);
							// String subsec = UtilImport.obtenPadreEnumeration(
							// ledger_repo, area.getEnumId());
							// String sec = UtilImport.obtenPadreEnumeration(
							// ledger_repo, subsec);

							// ACCTG_TRANS_PRESUPUESTAL
							Debug.log("ACCTG_TRANS_PRESUPUESTAL");
							AcctgTransPresupuestal aux = new AcctgTransPresupuestal();
							aux.setAcctgTransId(presupuestoEgreso
									.getAcctgTransId());
							// clasificacionesdata.getCiclo());
							aux.setClasificacion1(rowdata.getClasificacion1());
							aux.setClasificacion2(rowdata.getClasificacion2());
							aux.setClasificacion3(rowdata.getClasificacion3());
							aux.setClasificacion4(rowdata.getClasificacion4());
							aux.setClasificacion5(rowdata.getClasificacion5());
							aux.setClasificacion6(rowdata.getClasificacion5());
							aux.setClasificacion7(rowdata.getClasificacion6());
							aux.setClasificacion8(rowdata.getClasificacion7());
							aux.setClasificacion9(rowdata.getClasificacion8());
							aux.setClasificacion10(rowdata.getClasificacion9());
							aux.setClasificacion11(rowdata.getClasificacion10());
							aux.setClasificacion12(rowdata.getClasificacion11());
							aux.setClasificacion13(rowdata.getClasificacion12());
							aux.setClasificacion14(rowdata.getClasificacion13());
							aux.setClasificacion15(rowdata.getClasificacion14());
							aux.setClavePres(contenedor.getClavePresupuestal());
							aux.setAgrupador(rowdata.getAgrupador());
							aux.setLote(lote);
							imp_tx2 = this.session.beginTransaction();
							ledger_repo.createOrUpdate(aux);
							imp_tx2.commit();

							// History
							Debug.log("Busca periodos");
							List<CustomTimePeriod> periodos = UtilImport
									.obtenPeriodos(ledger_repo,
											organizationPartyId,
											presupuestoEgreso.getPostedDate());

							// C/D
							Debug.log("Obtencion de Cuentas Dinamico");
							String seqId = "00001", flag = "D", cuenta = miniguia
									.getCuentaCargo(), naturaleza = "D", tipo = "Debit";

							for (int j = 0; j < 2; j++) {
								if (j != 0) {
									seqId = "00002";
									flag = "C";
									cuenta = miniguia.getCuentaAbono();
									naturaleza = "A";
									tipo = "Credit";
								}
								AcctgTransEntry acctgentry = UtilImport
										.generaAcctgTransEntry(
												presupuestoEgreso,
												organizationPartyId, seqId,
												flag, cuenta, "CHUBBY");
								// clasificaciones
								// flag, cuenta, sfe.getEnumId());
								// Tags seteados.
								for (int i = 0; i < contenedor.getEnumeration()
										.size(); i++) {
									String indice = new Integer(i + 1)
											.toString();
									String campo = "acctgTagEnumId" + indice;
									acctgentry.set(campo, contenedor
											.getEnumeration().get(i)
											.getEnumId());
								}
								imp_tx3 = this.session.beginTransaction();
								ledger_repo.createOrUpdate(acctgentry);
								imp_tx3.commit();

								// GlAccountOrganization
								Debug.log("Empieza GlAccountOrganization");
								GlAccountOrganization glAccountOrganization = UtilImport
										.actualizaGlAccountOrganization(
												ledger_repo, presupuestoEgreso
														.getPostedAmount(),
												cuenta, organizationPartyId,
												naturaleza);
								imp_tx4 = this.session.beginTransaction();
								ledger_repo
										.createOrUpdate(glAccountOrganization);
								imp_tx4.commit();

								// GlAccountHistory
								Debug.log("Busca histories");
								List<GlAccountHistory> histories = UtilImport
										.actualizaGlAccountHistories(
												ledger_repo, periodos, cuenta,
												presupuestoEgreso
														.getPostedAmount(),
												tipo);
								Debug.log("Se impactan las histories regresadas");
								for (GlAccountHistory history : histories) {
									Transaction txHistory = null;
									txHistory = this.session.beginTransaction();
									ledger_repo.createOrUpdate(history);
									txHistory.commit();
								}
							}
						}

						if (mensaje.isEmpty()) {
							String message = "Successfully imported Presupuesto Egreso ["
									+ "].";
							// clasificadores
							// + rowdata.getClavePres() + "].";
							this.storeImportPresupuestoEgresoSuccess(rowdata,
									imp_repo);
							Debug.logInfo(message, MODULE);
							imported = imported + 1;
						}
					} catch (Exception ex) {
						String message = ex.getMessage();
						storeImportPresupuestoEgresoError(rowdata, message,
								imp_repo);

						// rollback all if there was an error when importing
						// item
						if (imp_tx1 != null) {
							imp_tx1.rollback();
						}
						if (imp_tx2 != null) {
							imp_tx2.rollback();
						}
						if (imp_tx3 != null) {
							imp_tx3.rollback();
						}
						if (imp_tx4 != null) {
							imp_tx4.rollback();
						}

						Debug.logError(ex, message, MODULE);
						throw new ServiceException(ex.getMessage());
					}
				}

				// Se inserta el Lote.
				if (!lote.equalsIgnoreCase("X") && loteValido) {
					LoteTransaccion loteTrans = new LoteTransaccion();
					loteTrans.setIdLote(lote);
					loteTrans.setTipoTransaccion("PresupuestoEgreso");
					Transaction transLote = null;
					transLote = this.session.beginTransaction();
					ledger_repo.createOrUpdate(loteTrans);
					transLote.commit();
				}
			}
			this.importedRecords = imported;

		} catch (InfrastructureException ex) {
			Debug.logError(ex, MODULE);
			throw new ServiceException(ex.getMessage());
		} catch (RepositoryException ex) {
			Debug.logError(ex, MODULE);
			throw new ServiceException(ex.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Helper method to store Presupuesto Egreso import success into
	 * <code>DataImportPresupuestoEgreso</code> entity row.
	 * 
	 * @param rowdata
	 *            item of <code>DataImportPresupuestoEgreso</code> entity that
	 *            was successfully imported
	 * @param imp_repo
	 *            repository of accounting
	 * @throws org.opentaps.foundation.repository.RepositoryException
	 */
	private void storeImportPresupuestoEgresoSuccess(
			DataImportPresupuestoEgreso rowdata,
			PresupuestoEgresoDataImportRepositoryInterface imp_repo)
			throws RepositoryException {
		// mark as success
		rowdata.setImportStatusId(StatusItemConstants.Dataimport.DATAIMP_IMPORTED);
		rowdata.setImportError(null);
		rowdata.setProcessedTimestamp(UtilDateTime.nowTimestamp());
		imp_repo.createOrUpdate(rowdata);
	}

	/**
	 * Helper method to store Presupuesto Egreso import error into
	 * <code>DataImportPresupuestoEgreso</code> entity row.
	 * 
	 * @param rowdata
	 *            item of <code>DataImportPresupuestoEgreso</code> entity that
	 *            was unsuccessfully imported
	 * @param message
	 *            error message
	 * @param imp_repo
	 *            repository of accounting
	 * @throws org.opentaps.foundation.repository.RepositoryException
	 */
	private void storeImportPresupuestoEgresoError(
			DataImportPresupuestoEgreso rowdata, String message,
			PresupuestoEgresoDataImportRepositoryInterface imp_repo)
			throws RepositoryException {
		// store the exception and mark as failed
		rowdata.setImportStatusId(StatusItemConstants.Dataimport.DATAIMP_FAILED);
		rowdata.setImportError(message);
		rowdata.setProcessedTimestamp(UtilDateTime.nowTimestamp());
		imp_repo.createOrUpdate(rowdata);
	}

}
