export interface IBII {
  id?: string;
  name?: string;
  type?: string;
  biiId?: string;
  detectionTimestamp?: number;
  sourceId?: string;
  detectionSystemName?: string;
  detectedValue?: string;
  detectionContext?: string;
  etc?: string;
  etcetc?: string;
}

export const defaultValue: Readonly<IBII> = {};
