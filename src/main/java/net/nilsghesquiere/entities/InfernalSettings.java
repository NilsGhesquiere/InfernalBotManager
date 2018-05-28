package net.nilsghesquiere.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.nilsghesquiere.web.dto.InfernalSettingsDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name ="infernalsettings")
@EqualsAndHashCode(exclude={"user"})
@ToString(exclude="user")
public class InfernalSettings implements Serializable{
	private static final long serialVersionUID = 1L;
	//Manager VARS
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid")
	@JsonIgnore
	private User user;
	//this is basicly the name of the settings, in infernalbot this will always have to be Default but here we will give it different names
	private String sets;
	//SystemSettings 
	private Integer groups;
	private String clientPath;
	private String clientVersion;
	private Integer timeSpan;
	private Boolean autoLogin;
	private Boolean autoBotStart;
	//ImExPortSettings VARS
	private String wildcard;
	//AutoqueuerSettings VARS
	private Boolean surrender;
	private Integer levelToBeginnerBot;
	private Boolean clientHide;
	private Boolean leaderHide;
	private Boolean consoleHide;
	private Boolean softEndDefault;
	private Integer softEndValue;	
	private Boolean queuerAutoClose;
	private Integer queueCloseValue;
	private Boolean winReboot;
	private Boolean winShutdown;
	private Boolean timeUntilCheck;
	private String  timeUntilReboot; 
	//PerformanceSettings VARS
	private Boolean renderDisable;
	private Boolean leaderRenderDisable;
	private Boolean cpuBoost;
	private Boolean leaderCpuBoost;
	private Boolean ramManager;
	private Integer ramMin;
	private Integer ramMax;
	//TimeoutsSettings VARS
	private Integer timeoutLogin;
	private Integer timeoutLobby;
	private Integer timeoutChamp;
	private Integer timeoutMastery;
	private Integer timeoutLoadGame;
	private Integer timeoutInGame;
	private Integer timeoutInGameFF;
	private Integer timeoutEndOfGame;
	//ChestSettings VARS
	private Boolean openChest;
	private Boolean openHexTech;
	private Boolean disChest;
	//OTHER VARS (Not in API)
	private Boolean replaceConfig;
	private Integer lolHeight;
	private Integer lolWidth;
	//new 29/03/2018
	private Boolean enableAutoExport;
	private String exportPath;
	private String exportWildCard;
	private Boolean exportRegion;
	private Boolean exportLevel;
	private Boolean exportBE;

	//TODO: API SETTINGS
	
	public InfernalSettings(){} 
	public InfernalSettings(User user) {
		super();
		this.user= user;
		this.sets = "Default";
		this.groups = 1;
		this.clientPath = "C:\\Riot Games\\League of Legends";
		this.wildcard = ":";
		this.replaceConfig = false;
		this.lolHeight = 240;
		this.lolWidth = 320;
		this.clientHide = true;
		this.consoleHide = true;
		this.ramManager = true;
		this.ramMin = 200;
		this.ramMax = 300;
		this.leaderHide = true;
		this.surrender = false;
		this.renderDisable = true;
		this.leaderRenderDisable = true;
		this.cpuBoost = true;
		this.leaderCpuBoost = true;
		this.levelToBeginnerBot = 99;
		this.timeSpan = 30;
		this.softEndDefault = true;
		this.softEndValue = 5;
		this.queuerAutoClose = false;
		this.queueCloseValue = 1;
		this.winReboot = true;
		this.winShutdown = false;
		this.timeoutLogin = 5;
		this.timeoutLobby = 5;
		this.timeoutChamp = 5;
		this.timeoutMastery = 5;
		this.timeoutLoadGame = 5;
		this.timeoutInGame = 60;
		this.timeoutInGameFF = 17;
		this.timeoutEndOfGame = 5;
		this.timeUntilCheck = false;
		this.timeUntilReboot = "01:00";
		this.openChest = true;
		this.openHexTech = true;
		this.disChest = true;
		this.enableAutoExport = false;
		this.exportPath = "";
		this.exportWildCard = ";";
		this. exportRegion= false;
		this.exportLevel = false;
		this.exportBE = false;
	}
	
	public InfernalSettings(Long id, User user, String sets, Integer groups,
			String clientPath, String clientVersion, Integer timeSpan,
			Boolean autoLogin, Boolean autoBotStart, String wildcard, Boolean surrender,
			Integer levelToBeginnerBot, Boolean clientHide, Boolean leaderHide,
			Boolean consoleHide, Boolean softEndDefault, Integer softEndValue,
			Boolean queuerAutoClose, Integer queueCloseValue,
			Boolean winReboot, Boolean winShutdown, Boolean timeUntilCheck,
			String timeUntilReboot, Boolean renderDisable,
			Boolean leaderRenderDisable, Boolean cpuBoost,
			Boolean leaderCpuBoost, Boolean ramManager, Integer ramMin,
			Integer ramMax, Integer timeoutLogin, Integer timeoutLobby,
			Integer timeoutChamp, Integer timeoutMastery,
			Integer timeoutLoadGame, Integer timeoutInGame,
			Integer timeoutInGameFF, Integer timeoutEndOfGame,
			Boolean openChest, Boolean openHexTech, Boolean disChest,
			Boolean replaceConfig, Integer lolHeight,
			Integer lolWidth, Boolean enableAutoExport, String exportPath,
			String exportWildCard, Boolean exportRegion, Boolean exportLevel,
			Boolean exportBE) {
		super();
		this.id = id;
		this.user = user;
		this.sets = sets;
		this.groups = groups;
		this.clientPath = clientPath;
		this.clientVersion = clientVersion;
		this.timeSpan = timeSpan;
		this.autoLogin = autoLogin;
		this.autoBotStart = autoBotStart;
		this.wildcard = wildcard;
		this.surrender = surrender;
		this.levelToBeginnerBot = levelToBeginnerBot;
		this.clientHide = clientHide;
		this.leaderHide = leaderHide;
		this.consoleHide = consoleHide;
		this.softEndDefault = softEndDefault;
		this.softEndValue = softEndValue;
		this.queuerAutoClose = queuerAutoClose;
		this.queueCloseValue = queueCloseValue;
		this.winReboot = winReboot;
		this.winShutdown = winShutdown;
		this.timeUntilCheck = timeUntilCheck;
		this.timeUntilReboot = timeUntilReboot;
		this.renderDisable = renderDisable;
		this.leaderRenderDisable = leaderRenderDisable;
		this.cpuBoost = cpuBoost;
		this.leaderCpuBoost = leaderCpuBoost;
		this.ramManager = ramManager;
		this.ramMin = ramMin;
		this.ramMax = ramMax;
		this.timeoutLogin = timeoutLogin;
		this.timeoutLobby = timeoutLobby;
		this.timeoutChamp = timeoutChamp;
		this.timeoutMastery = timeoutMastery;
		this.timeoutLoadGame = timeoutLoadGame;
		this.timeoutInGame = timeoutInGame;
		this.timeoutInGameFF = timeoutInGameFF;
		this.timeoutEndOfGame = timeoutEndOfGame;
		this.openChest = openChest;
		this.openHexTech = openHexTech;
		this.disChest = disChest;
		this.replaceConfig = replaceConfig;
		this.lolHeight = lolHeight;
		this.lolWidth = lolWidth;
		this.enableAutoExport = enableAutoExport;
		this.exportPath = exportPath;
		this.exportWildCard = exportWildCard;
		this. exportRegion= exportRegion;
		this.exportLevel = exportLevel;
		this.exportBE = exportBE;
	}
	
	public InfernalSettings(InfernalSettingsDTO dto){
		if (dto.getId() != 0){
			this.id = dto.getId(); 
		}
		this.sets = dto.getSets();
		this.groups = dto.getGroups();
		this.clientPath = dto.getClientPath();
		this.clientVersion = dto.getClientVersion();
		this.timeSpan = dto.getTimeSpan();
		this.autoLogin = dto.getAutoLogin();
		this.autoBotStart = dto.getAutoBotStart();
		this.wildcard = dto.getWildcard();
		this.surrender = dto.getSurrender();
		this.levelToBeginnerBot = dto.getLevelToBeginnerBot();
		this.clientHide = dto.getClientHide();
		this.leaderHide = dto.getLeaderHide();
		this.consoleHide = dto.getConsoleHide();
		this.softEndDefault = dto.getSoftEndDefault();
		this.softEndValue = dto.getSoftEndValue();
		this.queuerAutoClose = dto.getQueuerAutoClose();
		this.queueCloseValue = dto.getQueueCloseValue();
		this.winReboot = dto.getWinReboot();
		this.winShutdown = dto.getWinShutdown();
		this.timeUntilCheck = dto.getTimeUntilCheck();
		this.timeUntilReboot = dto.getTimeUntilReboot();
		this.renderDisable = dto.getRenderDisable();
		this.leaderRenderDisable = dto.getLeaderRenderDisable();
		this.cpuBoost = dto.getCpuBoost();
		this.leaderCpuBoost = dto.getLeaderCpuBoost();
		this.ramManager = dto.getRamManager();
		this.ramMin = dto.getRamMin();
		this.ramMax = dto.getRamMax();
		this.timeoutLogin = dto.getTimeoutLogin();
		this.timeoutLobby = dto.getTimeoutLobby();
		this.timeoutChamp = dto.getTimeoutChamp();
		this.timeoutMastery = dto.getTimeoutMastery();
		this.timeoutLoadGame = dto.getTimeoutLoadGame();
		this.timeoutInGame = dto.getTimeoutInGame();
		this.timeoutInGameFF = dto.getTimeoutInGameFF();
		this.timeoutEndOfGame = dto.getTimeoutEndOfGame();
		this.openChest = dto.getOpenChest();
		this.openHexTech = dto.getOpenHexTech();
		this.disChest = dto.getDisChest();
		this.enableAutoExport = dto.getEnableAutoExport();
		this.exportPath = dto.getExportPath();
		this.exportWildCard = dto.getExportWildCard();
		this.exportRegion= dto.getExportRegion();
		this.exportLevel = dto.getExportLevel();
		this.exportBE = dto.getExportBE();
	}
	
	public void updateFromDTO(InfernalSettingsDTO dto){
		this.sets = dto.getSets();
		this.groups = dto.getGroups();
		this.clientPath = dto.getClientPath();
		this.clientVersion = dto.getClientVersion();
		this.timeSpan = dto.getTimeSpan();
		this.autoLogin = dto.getAutoLogin();
		this.autoBotStart = dto.getAutoBotStart();
		this.wildcard = dto.getWildcard();
		this.surrender = dto.getSurrender();
		this.levelToBeginnerBot = dto.getLevelToBeginnerBot();
		this.clientHide = dto.getClientHide();
		this.leaderHide = dto.getLeaderHide();
		this.consoleHide = dto.getConsoleHide();
		this.softEndDefault = dto.getSoftEndDefault();
		this.softEndValue = dto.getSoftEndValue();
		this.queuerAutoClose = dto.getQueuerAutoClose();
		this.queueCloseValue = dto.getQueueCloseValue();
		this.winReboot = dto.getWinReboot();
		this.winShutdown = dto.getWinShutdown();
		this.timeUntilCheck = dto.getTimeUntilCheck();
		this.timeUntilReboot = dto.getTimeUntilReboot();
		this.renderDisable = dto.getRenderDisable();
		this.leaderRenderDisable = dto.getLeaderRenderDisable();
		this.cpuBoost = dto.getCpuBoost();
		this.leaderCpuBoost = dto.getLeaderCpuBoost();
		this.ramManager = dto.getRamManager();
		this.ramMin = dto.getRamMin();
		this.ramMax = dto.getRamMax();
		this.timeoutLogin = dto.getTimeoutLogin();
		this.timeoutLobby = dto.getTimeoutLobby();
		this.timeoutChamp = dto.getTimeoutChamp();
		this.timeoutMastery = dto.getTimeoutMastery();
		this.timeoutLoadGame = dto.getTimeoutLoadGame();
		this.timeoutInGame = dto.getTimeoutInGame();
		this.timeoutInGameFF = dto.getTimeoutInGameFF();
		this.timeoutEndOfGame = dto.getTimeoutEndOfGame();
		this.openChest = dto.getOpenChest();
		this.openHexTech = dto.getOpenHexTech();
		this.disChest = dto.getDisChest();
		this.enableAutoExport = dto.getEnableAutoExport();
		this.exportPath = dto.getExportPath();
		this.exportWildCard = dto.getExportWildCard();
		this.exportRegion= dto.getExportRegion();
		this.exportLevel = dto.getExportLevel();
		this.exportBE = dto.getExportBE();
	}
	
	public void setUser(User user) {
		if (this.user != null && this.user.getInfernalSettingsList().contains(this)){
			this.user.removeInfernalSettings(this);
		}
		this.user = user;
		if (user != null && !user.getInfernalSettingsList().contains(this)){
			user.addInfernalSettings(this);
		}
	}
	
	
}
